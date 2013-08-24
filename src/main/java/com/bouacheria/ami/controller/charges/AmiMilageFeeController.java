package com.bouacheria.ami.controller.charges;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.domain.amiservices.AmiCharge;
import com.bouacheria.ami.domain.amiservices.AmiFee;
import com.bouacheria.ami.domain.amiservices.AmiMilageFeeModel;
import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.service.amiservice.AmiChargeService;
import com.bouacheria.ami.service.amiservice.AmiFeeService;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;
import com.bouacheria.ami.service.cases.CaseService;

@Controller
public class AmiMilageFeeController 
{

	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private AmiServiceServiceCached cachedServicesService;
	
	@Autowired
	private AmiFeeService amiFeeService;
	
	@Autowired
	private AmiChargeService amiChargeService;
	

	@ModelAttribute("milageFeeModel")
	public AmiMilageFeeModel createAmiChargesModel() 
	{
		return new AmiMilageFeeModel();
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/addMilageFee", method = RequestMethod.GET, params ={"caseId","requestId"})
	public String goToAddMilageFee(Model model, @ModelAttribute @Valid AmiMilageFeeModel milageFeeModel, long caseId, long requestId ) 
	{
		
		milageFeeModel.setCaseId(caseId);
		milageFeeModel.setRequestId(requestId);
		
		AmiFee milageFee = cachedServicesService.getDefaultMilage();
		
		milageFeeModel.setDollarAmount(milageFee.getDollarAmount());
		milageFeeModel.setAmount(milageFee.getAmount());
		milageFeeModel.setPerAmount(milageFee.getPerAmount());
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		model.addAttribute("milageFeeModel", milageFeeModel);
		
		return "addMilageFee";
	}
	
	
	
	@RequestMapping(value = "/addMilageFee", method = RequestMethod.POST , params="addCharge")
	public String addMilageFeeCharge(Model model, @ModelAttribute @Valid AmiMilageFeeModel milageFeeModel, String addCharge, boolean readonly)
	{
		AmiFee milageFee = cachedServicesService.getDefaultMilage();
		milageFeeModel.setAmiFee(milageFee);
		
		//AmiCharge amiCharge = amiChargeService.getChargeFromFee(milageFee ,milageFeeModel.getCaseId(), milageFeeModel.getRequestId());
		AmiCharge amiCharge = amiChargeService.getChargeFromFee(milageFeeModel);
		amiChargeService.save(amiCharge);
		
		Case aCase = caseService.findById(milageFeeModel.getCaseId());
		if(aCase.isTranscriptionDone())
		{
			return "redirect:case?readonlyadmin="+milageFeeModel.getCaseId();
		}
		
		return "redirect:case?reviewCharges="+milageFeeModel.getCaseId();
		
	}
//	@RequestMapping(value = "/addMilageFeeReadonly", method = RequestMethod.POST , params="addCharge")
//	public String addMilageFeeToChargeReadonlyCase(Model model, @ModelAttribute @Valid AmiMilageFeeModel milageFeeModel, String addCharge, boolean readonly)
//	{
//		AmiFee milageFee = cachedServicesService.getDefaultMilage();
//		milageFeeModel.setAmiFee(milageFee);
//		
//		//AmiCharge amiCharge = amiChargeService.getChargeFromFee(milageFee ,milageFeeModel.getCaseId(), milageFeeModel.getRequestId());
//		AmiCharge amiCharge = amiChargeService.getChargeFromFee(milageFeeModel);
//		amiChargeService.save(amiCharge);
//		
//		return "redirect:caseReadonlyAdmin?readonlyadmin="+milageFeeModel.getCaseId();
//	}
	
}
