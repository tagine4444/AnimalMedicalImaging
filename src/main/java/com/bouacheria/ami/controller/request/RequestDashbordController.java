package com.bouacheria.ami.controller.request;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.service.request.ServiceRequestService;

@Controller
public class RequestDashbordController extends AbstractAmiController{


	private static final Logger logger = LoggerFactory.getLogger(ServiceRequestController.class);
	
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/requestDashboard", method = RequestMethod.GET)
	public String requestDashboard(Model model) 
	{
		List<ServiceRequest> statToProcess = serviceRequestService.findStatToReadByRadiologist();
		List<ServiceRequest> nonStatToProcess = serviceRequestService.findNonStatToReadByRadiologist();
		List<ServiceRequest> nonStatToProcessHigh = serviceRequestService.findNonStatToReadByRadiologistHighPriority();
		
		List<ServiceRequest> allReqWhichDocsArentDoneUploading =  serviceRequestService.findStatToReadByRadiologistWithDocsNotuploadedYet();
		
		
		
		model.addAttribute("statToProcess",statToProcess);
		model.addAttribute("hasStatToProcess",statToProcess!=null && statToProcess.size()>0);

		model.addAttribute("nonStatToProcess",nonStatToProcess);
		model.addAttribute("hasNonStatToProcess",nonStatToProcess!=null && nonStatToProcess.size()>0);
		
		model.addAttribute("nonStatToProcessHigh",nonStatToProcessHigh);
		model.addAttribute("hasNonStatToProcessPriorityHigh",nonStatToProcessHigh!=null && nonStatToProcessHigh.size()>0);
		
		model.addAttribute("allReqWhichDocsArentDoneUploading",allReqWhichDocsArentDoneUploading);
		model.addAttribute("hasReqWhichDocsArentDoneUploading",allReqWhichDocsArentDoneUploading!=null && allReqWhichDocsArentDoneUploading.size()>0);
		
		return "requestDashboard";
	}

}
