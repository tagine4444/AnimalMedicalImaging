package com.bouacheria.ami.controller.hospital.interpretation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.domain.Interpretation;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.service.request.ServiceRequestService;

@Controller
public class AMIInterpretationController {

	@Autowired
	private ServiceRequestService serviceRequestService;
	
	
	@ModelAttribute("interpretation")
	public Interpretation createInterpretation() 
	{
		return new Interpretation();
	}
	
	
	@RequestMapping(value = "/amiInterpretation", method = RequestMethod.GET)
	public String home(Model model, @ModelAttribute Interpretation interpretation ) 
	{
		List<ServiceRequest> statRequestCompleted = serviceRequestService.findStatReqTranscribedTodayForAMI();
		List<ServiceRequest> requestCompleted     = serviceRequestService.findReqTranscribedTodayForAMI();
		
		interpretation.setStatRequestCompleted(statRequestCompleted);
		interpretation.setRequestCompleted(requestCompleted);
		
		return "amiInterpretation";
	}
	
}
