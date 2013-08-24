package com.bouacheria.ami.controller.hospital.pendingrequest;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.domain.PendingRequest;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.request.ServiceRequestService;

@Controller
public class HospitalPendingRequestController {

	@Autowired
	private ServiceRequestService requestService;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	
	@ModelAttribute("pendingRequest")
	public PendingRequest createPendingRequest() 
	{
		return new PendingRequest();
	}
	
	
	@RequestMapping(value = "/hospitalPendingRequest", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @ModelAttribute PendingRequest pendingRequest ) {
		
		long hospitalId = securityUtil.getHospitalForLoggedinUser().getId();
		
		List<ServiceRequest> satServiceRequests = requestService.findStatNotYetReadYet(hospitalId);
		pendingRequest.setStatRequests(satServiceRequests);
		
		List<ServiceRequest> serviceRequests = requestService.findNonStatNotYetReadYet(hospitalId);
		pendingRequest.setRequests(serviceRequests);
		
		return "hospitalPendingRequest";
	}
}
