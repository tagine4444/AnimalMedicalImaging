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
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.request.ServiceRequestService;

@Controller
public class HospitalInterpretationController {

	@Autowired
	private ServiceRequestService serviceRequestService;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	
	@ModelAttribute("interpretation")
	public Interpretation createInterpretation() 
	{
		return new Interpretation();
	}
	
	
	@RequestMapping(value = "/hospitalInterpretation", method = RequestMethod.GET)
	public String home(Model model, @ModelAttribute Interpretation interpretation ) 
	{
		
		final long hospitalId = securityUtil.getHospitalForLoggedinUser().getId();
		
		List<ServiceRequest> statRequestCompleted = serviceRequestService.findStatReqTranscribedTodayForHospital(hospitalId);
		List<ServiceRequest> requestCompleted     = serviceRequestService.findReqTranscribedTodayForHospital(hospitalId);
		
		interpretation.setStatRequestCompleted(statRequestCompleted);
		interpretation.setRequestCompleted(requestCompleted);
		
		return "hospitalInterpretation";
	}
	
}
