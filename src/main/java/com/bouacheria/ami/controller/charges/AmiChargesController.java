package com.bouacheria.ami.controller.charges;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.domain.amiservices.AmiCharge;
import com.bouacheria.ami.domain.amiservices.AmiChargeTotal;
import com.bouacheria.ami.domain.amiservices.AmiChargesModel;
import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.service.amiservice.AmiChargeService;
import com.bouacheria.ami.service.amiservice.AmiChargeTotalService;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;
import com.bouacheria.ami.service.cases.CaseService;
import com.bouacheria.ami.service.datatype.AmiServiceCategory;

@Controller
public class AmiChargesController 
{

	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private AmiServiceServiceCached cachedServicesService;
	
	@Autowired
	private AmiChargeService amiChargeService;
	
	@Autowired
	private AmiChargeTotalService amiChargeTotalService;
	
	@Autowired
	private ConversionService conversionService;
	
//	@InitBinder
//	public void registerConversionService(WebDataBinder databinder)
//	{
//		databinder.setConversionService(conversionService);
//	}
	
	@ModelAttribute("amiChargesModel")
	public AmiChargesModel createAmiChargesModel() 
	{
		return new AmiChargesModel();
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addCharges", method = RequestMethod.GET, params ={"caseId","requestId"})
	public String goToAddCharges(Model model, @ModelAttribute @Valid AmiChargesModel amiChargesModel, long caseId, long requestId ) 
	{
		
		amiChargesModel.setCaseId(caseId);
		amiChargesModel.setRequestId(requestId);
		
		List<AmiCharge> amiCharges = amiChargeService.findByAmiCaseId(caseId);
		//model.addAttribute("amiCharges", amiCharges);
		amiChargesModel.setAmiCharges(amiCharges);
		
		AmiChargeTotal amiChargesTotal = amiChargeTotalService.findByAmiCaseId(caseId);
		model.addAttribute("amiChargesTotal", amiChargesTotal);
		amiChargesModel.setAmiChargesTotal(amiChargesTotal);
		
		
		List<AmiService> interpretationOnlySvcList = cachedServicesService.getInterpretationOnlyAmiServiceList();
		List<AmiService> ultrasoundSvcList = cachedServicesService.getUltrasoundSvcAmiServiceList();
		List<AmiService> radioFluoSvcList = cachedServicesService.getRadiographyFluoroscopyAmiServiceList();
		List<AmiService> contrastRadioSvcList = cachedServicesService.getContrastRadiographyAmiServiceList();
		List<AmiService> computedTomoSvcList = cachedServicesService.getComputedTomographyAmiServiceList();
		List<AmiService> MRISvcList = cachedServicesService.getMRIAmiServiceList();
		
		amiChargesModel.setInterpretationOnlySvcList(interpretationOnlySvcList);
		amiChargesModel.setUltrasoundSvcList(ultrasoundSvcList);
		amiChargesModel.setRadioFluoSvcList(radioFluoSvcList);
		amiChargesModel.setComputedTomoSvcList(computedTomoSvcList);
		amiChargesModel.setContrastRadioSvcList(contrastRadioSvcList);
		amiChargesModel.setMRISvcList(MRISvcList);
		amiChargesModel.setLatePaymentFee("n");
		amiChargesModel.setMilageFee("n");
		
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "addCharges";
	}
	
	
	
	@RequestMapping(value = "/addCharges", method = RequestMethod.POST, params ="addInterpretationOnly")
	public String addInterpretationOnly(Model model, @ModelAttribute AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "addCharges";
		}
		
		AmiService mriSvc = amiChargesModel.getInterpretationOnlySvc();
		AmiCharge amiCharge = amiChargeService.getChargeFromService(mriSvc ,amiChargesModel.getCaseId(), amiChargesModel.getRequestId());
		amiChargeService.save(amiCharge);
		
		return "redirect:case?reviewCharges="+amiChargesModel.getCaseId();
	}
	
	@RequestMapping(value = "/addCharges", method = RequestMethod.POST, params ="addUltrasoundCharge")
	public String addUltrasoundCharge(Model model, @ModelAttribute AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "addCharges";
		}
		
		AmiService aServiceToCharge = amiChargesModel.getUltrasoundSvc();
		AmiCharge amiCharge = amiChargeService.getChargeFromService(aServiceToCharge ,amiChargesModel.getCaseId(), amiChargesModel.getRequestId());
		amiChargeService.save(amiCharge);
		
		return "redirect:case?reviewCharges="+amiChargesModel.getCaseId();
	}
	
	@RequestMapping(value = "/addCharges", method = RequestMethod.POST, params ="addMriCharge")
	public String addMriCharge(Model model, @ModelAttribute AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		//return addACharge(amiChargesModel.getMRISvc(),  amiChargesModel,  bindingResult);
		if(bindingResult.hasErrors())
		{
			return "addCharges";
		}
		
		AmiService mriSvc = amiChargesModel.getMRISvc();
		AmiCharge amiCharge = amiChargeService.getChargeFromService(mriSvc ,amiChargesModel.getCaseId(), amiChargesModel.getRequestId());
		amiChargeService.save(amiCharge);
		
		return "redirect:case?reviewCharges="+amiChargesModel.getCaseId();
	}
	
	
	@RequestMapping(value = "/addCharges", method = RequestMethod.POST, params ="addContrastRadioCharge")
	public String addContrastRadioCharge(Model model, @ModelAttribute AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		return addACharge(amiChargesModel.getContrastRadioSvc(),  amiChargesModel,  bindingResult);
	}
	
	@RequestMapping(value = "/addCharges", method = RequestMethod.POST, params ="addComputedTomoCharge")
	public String addComputedTomoCharge(Model model, @ModelAttribute AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		return addACharge(amiChargesModel.getComputedTomoSvc(),  amiChargesModel,  bindingResult);
	}
	
	@RequestMapping(value = "/addCharges", method = RequestMethod.POST, params ="addRadioFluoCharge")
	public String addRadioFluoCharge(Model model, @ModelAttribute AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		return addACharge(amiChargesModel.getRadioFluoSvc() ,  amiChargesModel,  bindingResult);
	}
	
	@RequestMapping(value = "/addCharges", method = RequestMethod.POST, params ="addMiscCharge")
	public String addMiscCharge(Model model, @ModelAttribute AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		//AmiService miscSvc = cachedServicesService.getMiscAmiService();
		AmiService miscSvc = new AmiService();
		
		miscSvc.setCategory(AmiServiceCategory.MISC_SERVICE.getCode());
		miscSvc.setName(AmiServiceCategory.MISC_SERVICE.getName());
		miscSvc.setPriceMin(amiChargesModel.getMiscPrice());
		miscSvc.setPriceMax(amiChargesModel.getMiscPrice());
		miscSvc.setDefaultPrice(amiChargesModel.getMiscPrice());
		miscSvc.setDescription(amiChargesModel.getMiscDescription());
		
		return addACharge(miscSvc ,  amiChargesModel,  bindingResult);
	}
	
	
	public String addACharge(AmiService aService, AmiChargesModel amiChargesModel,  BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
		{
			return "";
		}
		
		AmiCharge amiCharge = amiChargeService.getChargeFromService(aService ,amiChargesModel.getCaseId(), amiChargesModel.getRequestId());
		amiChargeService.save(amiCharge);
		//cachedServicesService.init();
		
		return "redirect:case?reviewCharges="+amiChargesModel.getCaseId();
	}
	
	
	@RequestMapping(value = "/deleteCharges", method = RequestMethod.GET, params ={"chargeId", "caseId"})
	public String deleteCharge(Model model, @ModelAttribute AmiChargesModel amiChargesModel, long chargeId,long caseId )
	{
		amiChargeService.delete(chargeId);
		
		return "redirect:case?reviewCharges="+caseId;
	}
	
	
}
