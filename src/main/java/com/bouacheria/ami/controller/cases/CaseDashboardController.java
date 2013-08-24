package com.bouacheria.ami.controller.cases;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.request.ServiceRequestController;
import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.service.cases.CaseService;

@Controller
public class CaseDashboardController {

private static final Logger logger = LoggerFactory.getLogger(ServiceRequestController.class);
	
	@Autowired
	private CaseService caseService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/caseDashboard", method = RequestMethod.GET)
	public String caseDashboard(Model model) 
	{
		List<Case> statCasesToTranscribe = caseService.findSTATCasesToTranscribe();
		List<Case> nonStatToTranscribe   = caseService.findCasesToTranscribe(ServiceRequest.PRIORITY_NORMAL);
		List<Case> nonStatToTranscribeHigh   = caseService.findCasesToTranscribe(ServiceRequest.PRIORITY_HIGH);
		
		model.addAttribute("statCasesToTranscribe",statCasesToTranscribe);
		model.addAttribute("hasStatCasesToTranscribe",statCasesToTranscribe!=null && statCasesToTranscribe.size()>0);

		model.addAttribute("nonStatToTranscribe",nonStatToTranscribe);
		model.addAttribute("hasNonStatToTranscribe",nonStatToTranscribe!=null && nonStatToTranscribe.size()>0);

		model.addAttribute("nonStatToTranscribeHigh",nonStatToTranscribeHigh);
		model.addAttribute("hasnonStatToTranscribeHigh",nonStatToTranscribeHigh!=null && nonStatToTranscribeHigh.size()>0);
		
		return "caseDashboard";
	}
}
