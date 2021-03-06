package com.bouacheria.ami.controller.request;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.repository.config.ConfigUtil;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.emails.EmailService;
import com.bouacheria.ami.service.request.RequestNumberGeneratorService;
import com.bouacheria.ami.service.request.ServiceRequestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ServiceRequestController 
//extends AbstractAmiController
{
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceRequestController.class);
	
	@Autowired
	private ServiceRequestService serviceRequestService;
	
//	@Autowired
//	private CaseService caseService;
	
	@Autowired
	private RequestNumberGeneratorService requestNumberGeneratorService;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
	@Autowired
	private ConfigUtil config;
	
	@ModelAttribute("serviceRequest")
	public ServiceRequest createServiceRequest() 
	{
		ServiceRequest request = new ServiceRequest();
		request.setHospitalAttribute(securityUtil.getHospitalForLoggedinUser().getHospitalAttribute());
		request.setUnderContract(securityUtil.getHospitalForLoggedinUser().isUnderContract());
		return request;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/serviceRequest", method = RequestMethod.GET)
	public String serviceRequest( Model model) 
	{
		//logger.info("Service Request");
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		return "serviceRequest";
	}

	@RequestMapping(value = "/serviceRequest", method = RequestMethod.POST, params = { "Save" })
	public String saveServiceRequest( Model model, @ModelAttribute @Valid ServiceRequest serviceRequest , BindingResult result) 
	{ 
		if(result.hasErrors())
		{
			serviceRequestAndCaseHelper.addRefDataToModel(model);
			return "serviceRequest";
		}
		
		try
		{
			
			String ageError = serviceRequest.isValidPatientAge();
			if(StringUtils.isNotEmpty(ageError))
			{
				serviceRequestAndCaseHelper.addRefDataToModel(model);
				model.addAttribute("serviceRequestMessage", "The patient age is Invalid. Click on the Help link next to the Patient Age label for valid examples.");
				model.addAttribute("ageError", ageError );
				return "serviceRequest";
			}
			if(!serviceRequest.isInterpretationOnly() && !serviceRequest.hasAtLeastOneImagingServie())
			{
				serviceRequestAndCaseHelper.addRefDataToModel(model);
				model.addAttribute("serviceRequestMessage", "You must select at least one service if you have selected the imaging service type");
				return "serviceRequest";
			}
			
			serviceRequest.upperCaseFields();
			
			
			
			Hospital hospital = securityUtil.getHospitalForLoggedinUser();
			Long hospitalId = hospital.getId();
			
			serviceRequest.setHospitalAttribute(hospital.getHospitalAttribute());
			serviceRequest.setPriority(hospital.getPriority());
			serviceRequest.setHospitalId(hospitalId);
			serviceRequest.setCaseStatus("Interpretation Requested");
			serviceRequest.setUnderContract(hospital.isUnderContract());
			serviceRequest.setRequestDate(new DateTime());
			
			if(!serviceRequest.hasRequestNumber() && hospital.isUnderContract())
			{
				String requestNumber = requestNumberGeneratorService.getRequestNumber(hospitalId, hospital.getAcronym());
				serviceRequest.setRequestNumber(requestNumber);
			}
			
			
			ServiceRequest savedReq = serviceRequestService.save(serviceRequest);
			// check if it already has a number (on update). if it does, don't change it
			if(!serviceRequest.hasRequestNumber() && !hospital.isUnderContract())
			{
				savedReq.setRequestNumber(String.valueOf(savedReq.getId()));
				
				ServiceRequest savedReq1 = serviceRequestService.save(serviceRequest);
				model.addAttribute("serviceRequest", savedReq1);
			}
			else
			{
				model.addAttribute("serviceRequest", savedReq);
				String emailBody = getEmailBody(savedReq , true);
				String stat = " ";
				if(savedReq.isStat())
				{
					stat = " STAT ";
				}
				
				String from = config.getAmiEmail();
				
				String hospitalEmail = hospital.getHospitalEmail();
				//String userEmail = securityUtil.getLoggedinUserEmail();
				
				String to = emailService.getTo(hospitalEmail);
	
				if(config.isEmailEnabled())
				{
					emailService.sendMail(from, to, "New"+stat+ "Service Request" + savedReq.getRequestNumber() , emailBody);
				}
			}
			
			if(savedReq.isDocByUpload())
			{
				return "redirect:upload?svcReqId="+savedReq.getId()+"&requestNumber="+savedReq.getRequestNumber();
			}
			return "redirect:hospitalPendingRequest";
		}
		catch (Exception e)
		{
			logger.error("SaveServiceRequest Failed",e);
			model.addAttribute("errorString", ExceptionUtils.getStackTrace(e));
			return "errorPage";
		}
	}
	
	private String getEmailBody(ServiceRequest savedReq, boolean isUpdate)
	{
		String stat = "";
		if(savedReq.isStat())
		{
			stat = "STAT ";
		}
		
		String email = stat + "Request " + savedReq.getRequestNumber() +" submitted by "+savedReq.getHospitalName() +"\n"
				+ "Patient \""+ savedReq.getPatientName() + "\" is a" +
				savedReq.getPatientSpecies() + " "+ savedReq.getPatientBreed() +"\n"+
				"Exam: "+ savedReq.getConsultation() +"\n"+
				"Tentative diagnosis: "+ savedReq.getTentativeDiagnosis();
		return email;
	}
	
	@RequestMapping(value = "/serviceRequest", method = RequestMethod.GET, params ="edit")
	public String editServiceRequest( Model model, @RequestParam long edit) 
	{
		ServiceRequest serviceReq = serviceRequestService.findById(edit);
		
		if(serviceReq.isInProgress())
		{
			return "redirect:hospitalPendingRequest";
		}
		
		model.addAttribute("serviceRequest", serviceReq);
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "serviceRequest";
	}
	
	@ExceptionHandler
	public String handleErrors(Model model)
	{
		model.addAttribute("errorString", "Some Error occured");
		
		return "errorPage";
	}
	
}
