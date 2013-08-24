package com.bouacheria.ami.controller;

import java.util.List;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.domain.amiservices.AmiServiceModel;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.amiservice.AmiServiceService;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;

@Controller
public class AmiServiceController {

	@Autowired
	private AmiServiceServiceCached amiServiceCache;
	
	@Autowired
	private AmiServiceService amiServiceService;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	
	@ModelAttribute("amiServiceModel")
	public AmiServiceModel createAmiChargesModel() 
	{
		return new AmiServiceModel();
	}
	
	@RequestMapping(value = "/amiServices", method = RequestMethod.GET)
	public String goToAmiService(Model model, @ModelAttribute AmiServiceModel amiServiceModel)
	{
		
		List<AmiService> allSvcsByCategory = amiServiceCache.getAllServices();
		List<String> serviceCategories     = amiServiceCache.getServiceCategories();
		
		model.addAttribute("amiServiceModel", amiServiceModel);
		model.addAttribute("serviceCategories", serviceCategories);
		model.addAttribute("allSvcsByCategory", allSvcsByCategory);
		
		return "amiServices";
	}
	
	@RequestMapping(value = "/amiServices", method = RequestMethod.POST , params ="addServices")
	public String addAmiService(Model model, @ModelAttribute @Valid AmiServiceModel amiServiceModel,BindingResult result)
	{
		List<AmiService> allSvcsByCategory = null;
		List<String> serviceCategories     = null;
		if(result.hasErrors())
		{
			allSvcsByCategory = amiServiceCache.getAllServices();
			serviceCategories = amiServiceCache.getServiceCategories();
			
			model.addAttribute("amiServiceModel", amiServiceModel);
			model.addAttribute("serviceCategories", serviceCategories);
			model.addAttribute("allSvcsByCategory", allSvcsByCategory);
			
			return "amiServices";
		}
		
		AmiService amiService = new AmiService().basedOnAmiService(amiServiceModel);
		amiServiceService.save(amiService);
		amiServiceCache.init();
		
		allSvcsByCategory = amiServiceCache.getAllServices();
		serviceCategories     = amiServiceCache.getServiceCategories();
		
		amiServiceModel.reset();
		model.addAttribute("amiServiceModel", amiServiceModel);
		model.addAttribute("serviceCategories", serviceCategories);
		model.addAttribute("allSvcsByCategory", allSvcsByCategory);
		
		return "amiServices";
	}
	
	
	
	@RequestMapping(value = "/amiServices", method = RequestMethod.GET , params ="delete")
	public String deleteAmiService(Model model, @ModelAttribute AmiServiceModel amiServiceModel, long delete)
	{
		AmiService amiService = amiServiceService.findById(delete);
		amiService.setDeactivatedDate(new DateTime());
		
		String deactivatedBy = securityUtil.getHospitalForLoggedinUser().getId() + "] [" +securityUtil.getHospitalForLoggedinUser().getUserName() + "]";
		amiService.setDeactivatedBy(deactivatedBy);
		amiServiceService.save(amiService);
		amiServiceCache.init();
		
		List<AmiService> allSvcsByCategory = amiServiceCache.getAllServices();
		List<String> serviceCategories     = amiServiceCache.getServiceCategories();
		
		amiServiceModel.reset();
		model.addAttribute("amiServiceModel", amiServiceModel);
		model.addAttribute("serviceCategories", serviceCategories);
		model.addAttribute("allSvcsByCategory", allSvcsByCategory);
		
		return "amiServices";
	}
	
	@RequestMapping(value = "/amiServices", method = RequestMethod.GET , params ="edit")
	public String goToEditAmiService(Model model, @ModelAttribute AmiServiceModel amiServiceModel, long edit)
	{
		AmiService amiService = amiServiceService.findById(edit);
		amiServiceModel.basedOnAmiService(amiService);
		
		List<String> serviceCategories     = amiServiceCache.getServiceCategories();
		
		model.addAttribute("amiServiceModel", amiServiceModel);
		model.addAttribute("serviceCategories", serviceCategories);
		
		return "editAmiServices";
	}
	
	@RequestMapping(value = "/amiServices", method = RequestMethod.POST , params ="saveEditedService")
	public String saveEditedAmiService(Model model, @ModelAttribute AmiServiceModel amiServiceModel)
	{
		AmiService amiService = amiServiceService.findById(amiServiceModel.getId());
		amiService.updateBasedOnAmiService(amiServiceModel);
		
		amiServiceService.save(amiService);
		amiServiceCache.init();
		
//		List<AmiService> allSvcsByCategory = amiServiceCache.getAllServices();
//		List<String> serviceCategories     = amiServiceCache.getServiceCategories();
//		
//		amiServiceModel.reset();
//		model.addAttribute("amiServiceModel", amiServiceModel);
//		model.addAttribute("serviceCategories", serviceCategories);
//		model.addAttribute("allSvcsByCategory", allSvcsByCategory);
		
		return "redirect:amiServices";
	}
	
	
}
