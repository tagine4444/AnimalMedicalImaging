package com.bouacheria.ami.controller.cases;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.controller.request.ServiceRequestController;
import com.bouacheria.ami.domain.amiservices.AmiCharge;
import com.bouacheria.ami.domain.amiservices.AmiChargeTotal;
import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.repository.config.ConfigUtil;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.amiservice.AmiChargeService;
import com.bouacheria.ami.service.amiservice.AmiChargeTotalService;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;
import com.bouacheria.ami.service.cases.CaseService;
import com.bouacheria.ami.service.cases.PdfGenerator;
import com.bouacheria.ami.service.datatype.AmiServiceCategory;
import com.bouacheria.ami.service.emails.EmailService;
import com.bouacheria.ami.service.request.ServiceRequestService;
import com.bouacheria.ami.service.users.UserService;

@Controller
public class CaseController {

	
	private static final Logger logger = LoggerFactory.getLogger(ServiceRequestController.class);
	
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private AmiChargeTotalService amiChargeTotalService;
	
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PdfGenerator pdfGen;
	
	@Autowired
	private AmiServiceServiceCached cachedServicesService;
	
	@Autowired
	private AmiChargeService amiChargeService;
	
	@Autowired
	private ConfigUtil config;
	
	
	@Resource(name = "amiProperties")
	private Properties amiProperties;
	
	
	@ModelAttribute("aCase")
	public Case createCase() 
	{
		return new Case();
	}
	
	@RequestMapping(value = "/case", method = RequestMethod.GET, params ="requestToRead")
	public String readRequest( Model model, @RequestParam long requestToRead) 
	{
		ServiceRequest serviceReq = serviceRequestService.findById(requestToRead);
		if(serviceReq.isInProgress())
		{
			// if here it means that the radiologist already started working on the case, and saved it as draft, so get it from db and return
			Case savedCaseRefreshed =  caseService.findById(serviceReq.getCaseId());
			model.addAttribute("aCase", savedCaseRefreshed);
			
			List<AmiCharge> amiCharges = amiChargeService.findByAmiCaseId(savedCaseRefreshed.getId());
			model.addAttribute("amiCharges", amiCharges);
			
			AmiChargeTotal amiChargesTotal = amiChargeTotalService.findByAmiCaseId(savedCaseRefreshed.getId());
			model.addAttribute("amiChargesTotal", amiChargesTotal);
			
			serviceRequestAndCaseHelper.addRefDataToModel(model);
			return  "case";
		}
		
		// if here it means that the radiologist clicked on the link to read the case for the 1st time, so we have to create it.
		// copy attribute from the service request onto the case & save the case
		Case aCase = new Case();
		aCase.setReadingInProgressDate(new DateTime());
		aCase.copy(serviceReq);
		Case savedCase = caseService.save(aCase);
		
		// update the service request to say it's in progress & link it to the caseId
		serviceReq.setInProgressDate(new DateTime());
		serviceReq.setCaseId(savedCase.getId());
		serviceReq.setCaseStatus("Interpretation In Progress");
		serviceRequestService.save(serviceReq);
		
		Case savedCaseRefreshed =  caseService.findById(savedCase.getId());
		model.addAttribute("aCase", savedCaseRefreshed);
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "case";
	}
	
	
	@RequestMapping(value = "/case", method = RequestMethod.POST, params ="saveComplete")
	public String saveComplete( Model model, @ModelAttribute Case aCase) 
	{
		Case dbCase = caseService.findById(aCase.getId());
		dbCase.setRadioImpression(aCase.getRadioImpression());
		dbCase.setRadioInterpretation(aCase.getRadioInterpretation());
		dbCase.setRecommendations(aCase.getRecommendations());
		dbCase.setAmendedVetNotes(aCase.getAmendedVetNotes());
		boolean wasNeverSaved = dbCase.isReadingNotDone();
		
		
		dbCase.setReadingInCompleteDate(new DateTime());
		Case savedCase = caseService.save(dbCase);
		
		ServiceRequest serviceReq = serviceRequestService.findById(dbCase.getRequestId());
		serviceReq.setCaseStatus("Ready for Transcription");
		serviceReq.setReadingInCompleteDate(new DateTime());
		serviceRequestService.save(serviceReq);
		
		List<AmiCharge> amiCharges =null;
		if(wasNeverSaved)
		{
			Map<AmiServiceCategory, String> serviceMap = serviceReq.getSelectedImagingServices();
			List<AmiService> selectedImagingServices = cachedServicesService.getSelectedImagingServices(serviceMap);
			amiCharges = amiChargeService.getChargesFromService(selectedImagingServices ,savedCase.getId(), serviceReq.getId());
			amiChargeService.save(amiCharges);
			
			AmiChargeTotal total = amiChargeService.getTotal(amiCharges, savedCase.getId());
			amiChargeTotalService.save(total);
		}
		
		if(savedCase.isTranscriptionDone())
		{
			return "redirect:case?readonlyadmin="+savedCase.getId();
		}
		
		return "redirect:case?reviewCharges="+aCase.getId();
		//return "redirect:requestDashboard";
	}
	
	@RequestMapping(value = "/case", method = RequestMethod.GET, params ="reviewCharges")
	public String reviewCharges( Model model, @RequestParam long reviewCharges) 
	{
		Case aCase = caseService.findById(reviewCharges);
		model.addAttribute("aCase", aCase);
		
		
		List<AmiCharge> amiCharges = amiChargeService.findByAmiCaseId(reviewCharges);
		model.addAttribute("amiCharges", amiCharges);
		
		AmiChargeTotal amiChargesTotal = amiChargeTotalService.findByAmiCaseId(reviewCharges);
		amiChargesTotal = amiChargeService.getTotal(amiCharges, aCase.getId());
		amiChargeTotalService.save(amiChargesTotal);
		model.addAttribute("amiChargesTotal", amiChargesTotal);
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "case";
	}
	
	private String getEmailBody(ServiceRequest serviceReq ) {
		StringBuilder sb = new StringBuilder();
		
		String clientId = serviceReq.getClientId();
		
		if(StringUtils.isEmpty(clientId))
		{
			clientId = "N/A" ;
		}
		
		String amiUrl = (String)amiProperties.get("amiUrl");
		
		sb.append("The request you submitted for: ");
		sb.append("Client Id: "+ clientId + " Patient: "+ serviceReq.getPatientName() + ", " + serviceReq.getPatientSpecies() + " " + serviceReq.getPatientBreed() );
		sb.append(" is ready.\nPlease login here " + amiUrl + " to see the result.");
		
		return sb.toString();
	}
	private String getEmailBody(Case aCase ) {
		StringBuilder sb = new StringBuilder();
		
		String clientId = aCase.getClientId();
		
		if(StringUtils.isEmpty(clientId))
		{
			clientId = "N/A" ;
		}
		
		String amiUrl = (String)amiProperties.get("amiUrl");
		
		sb.append("The request you submitted for: ");
		sb.append("Client Id: "+ clientId + " Patient: "+ aCase.getPatientName() + ", " + aCase.getPatientSpecies() + " " + aCase.getPatientBreed() );
		sb.append(" is ready.\nPlease login here " + amiUrl + " to see the result.");
		
		return sb.toString();
	}

	@RequestMapping(value = "/case", method = RequestMethod.POST, params ="saveDraft")
	public String saveDraft( Model model, @ModelAttribute Case aCase) 
	{
		Case dbCase = caseService.findById(aCase.getId());
		dbCase.setRadioImpression(aCase.getRadioImpression());
		dbCase.setRecommendations(aCase.getRecommendations());
		dbCase.setAmendedVetNotes(aCase.getAmendedVetNotes());
		
		caseService.save(dbCase);
		
		return "redirect:requestDashboard?readonly="+aCase.getId();
	}
	
	@RequestMapping(value = "/case", method = RequestMethod.POST, params ="saveTranscribed")
	public String saveTransription( Model model, @ModelAttribute Case aCase,HttpServletRequest request) 
	{
		Case dbCase = caseService.findById(aCase.getId());
		dbCase.setRadioImpression(aCase.getRadioImpression());
		dbCase.setRecommendations(aCase.getRecommendations());
		dbCase.setAmendedVetNotes(aCase.getAmendedVetNotes());
		
		dbCase.setTranscriptionCompleteDate(new DateTime());
		caseService.save(dbCase);
		
		ServiceRequest serviceReq = serviceRequestService.findById(dbCase.getRequestId());
		serviceReq.setCaseStatus("Transcription Done");
		serviceReq.setTrascribedDate(new DateTime());
		serviceRequestService.save(serviceReq);
		
		String emailFrom = config.getAmiEmail();
		//String emailTo = 
				
			String hospitalEmail =dbCase.getHospitalAttribute().getContact().getEmail();
			String emailTo = emailService.getTo(hospitalEmail);
//			FileSystemResource pdf = null;
//			try {
//				pdf = pdfGen.getPdf(aCase);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}	
			
		if(config.isEmailEnabled())
		{
			try
			{
				emailService.sendMail(emailFrom, emailTo,  "Your Request" + serviceReq.getRequestNumber() +" is ready" , getEmailBody(serviceReq) );
			}
			catch (Exception e)
			{
				model.addAttribute("error", "Customer will not get the email notification. Error: " + e.getMessage());
				e.printStackTrace();
			}
			
			try
			{
				pdfGen.emailPdf(dbCase,emailFrom, emailTo,  "Your Request" + serviceReq.getRequestNumber() +" is ready" , getEmailBody(serviceReq), request );
			}
			catch (Exception e)
			{
				model.addAttribute("error", "Customer will not get the PDF report email. Error: " + e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		return "redirect:caseDashboard";
	}
	
	
	
	
	@RequestMapping(value = "/case", method = RequestMethod.GET, params ="readonly")
	public String viewInterpretation( Model model, @RequestParam long readonly) 
	{
		Case aCase = caseService.findById(readonly);
		model.addAttribute("aCase", aCase);
		
		List<AmiCharge> amiCharges = amiChargeService.findByAmiCaseId(readonly);
		model.addAttribute("amiCharges", amiCharges);
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "caseReadonly";
	}
	
	@RequestMapping(value = "/case", method = RequestMethod.GET, params ="readonlyadmin")
	public String viewInterpretationAdmin( Model model, @RequestParam long readonlyadmin) 
	{
		Case aCase = caseService.findById(readonlyadmin);
		model.addAttribute("aCase", aCase);
		
		List<AmiCharge> amiCharges = amiChargeService.findByAmiCaseId(readonlyadmin);
		model.addAttribute("amiCharges", amiCharges);
		
		AmiChargeTotal amiChargesTotal = amiChargeTotalService.findByAmiCaseId(readonlyadmin);
		model.addAttribute("amiChargesTotal", amiChargesTotal);
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "caseReadonlyAdmin";
	}
	
	@RequestMapping(value = "/case", method = RequestMethod.POST, params="saveNotesReadOnlyCase")
	public String saveNotesReadOnlyCase( Model model,  @ModelAttribute Case myCase, @RequestParam boolean saveNotesReadOnlyCase) 
	{
		
		Case aCase = caseService.findById(myCase.getId());
		aCase.setNotes(myCase.getNotes());
		Case savedCase = caseService.save(aCase);
		
		model.addAttribute("aCase", savedCase);
		
		List<AmiCharge> amiCharges = amiChargeService.findByAmiCaseId(myCase.getId());
		model.addAttribute("amiCharges", amiCharges);
		
		AmiChargeTotal amiChargesTotal = amiChargeTotalService.findByAmiCaseId(myCase.getId());
		model.addAttribute("amiChargesTotal", amiChargesTotal);
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		model.addAttribute("notesMsg", "Notes Saved");
		
		return "caseReadonlyAdmin";
	}
	
	
	
	
	
	@RequestMapping(value = "/case", method = RequestMethod.POST, params ="capturedInQuickBook")
	public String updateCapturedInQuickBook( Model model, @ModelAttribute Case myCase, @RequestParam boolean capturedInQuickBook) 
	{
		long caseId = myCase.getId();
		
		Case aCase = caseService.findById(caseId);
		aCase.setCapturedInQuickBook(capturedInQuickBook);
		Case savedCase = caseService.save(aCase);
		
		model.addAttribute("aCase", savedCase);
		
		List<AmiCharge> amiCharges = amiChargeService.findByAmiCaseId(caseId);
		model.addAttribute("amiCharges", amiCharges);
		
		AmiChargeTotal amiChargesTotal = amiChargeTotalService.findByAmiCaseId(caseId);
		model.addAttribute("amiChargesTotal", amiChargesTotal);
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "caseReadonlyAdmin";
	}
	
	@RequestMapping(value = "/emailPdf", method = RequestMethod.GET, params ="caseId")
	public String emailPdf( Model model, @RequestParam long caseId,HttpServletRequest request) 
	{
		Case aCase = caseService.findById(caseId);
		
		String emailFrom = config.getAmiEmail();
		String loggedinUserName = securityUtil.getLoggedinUserName();
		AMIUser user = userService.findByUser(loggedinUserName);
		if(user==null || user.getEmail()==null)
		{
			System.out.println("user ==null or email == null" );
			model.addAttribute("caseMsg", "The email was not sent. The system doesn't have an email for the logged in user");
			return "emailConfirm";
		}
		String emailTo = aCase.getHospitalAttribute().getContact().getEmail();
		pdfGen.emailPdf(aCase,emailFrom, user.getEmail(),  "Your Request" + aCase.getRequestNumber() +" is ready" , getEmailBody(aCase),request );
		model.addAttribute("caseMsg", "Interpretation for request "+aCase.getRequestNumber()+" has been emailed to " + emailTo);
		return "emailConfirm";
	}
	
	@RequestMapping(value = "/deleteCharges", method = RequestMethod.POST, params ={"chargeId", "caseId","requestId"})
	public String deleteCharge(Model model, long chargeId,long caseId,long requestId )
	{
		Case aCase = caseService.findById(caseId);
		amiChargeService.delete(chargeId);
		
		if(aCase.isTranscriptionDone())
		{
			return "redirect:case?readonlyadmin="+aCase.getId();
		}
		
		return "redirect:case?reviewCharges="+caseId;
	}
	
}
