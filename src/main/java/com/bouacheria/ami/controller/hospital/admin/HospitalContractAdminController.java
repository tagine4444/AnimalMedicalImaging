package com.bouacheria.ami.controller.hospital.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.hospital.HospitalContractAdmin;
import com.bouacheria.ami.domain.request.RequestSequence;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.service.hospital.HospitalService;
import com.bouacheria.ami.service.request.RequestSequenceService;
import com.bouacheria.ami.service.users.UserService;

@Controller
public class HospitalContractAdminController 
{
	
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestSequenceService requestSequenceService;
	
	

	@ModelAttribute("hospitalContractAdmin")
	public HospitalContractAdmin createHospitalContractAdmin()
	{
		return new HospitalContractAdmin();
	}

	

	@RequestMapping(value="/hospitalContractAdmin", method = RequestMethod.GET, params ="updateContract")
	public String updateContract(Model model, @RequestParam long updateContract)
	{
		HospitalContractAdmin hospitalContractAdmin = new HospitalContractAdmin();
		hospitalContractAdmin.setHospitalId(updateContract);
		updateModel(model, hospitalContractAdmin);
		
		
		
		return "hospitalContractAdmin";
	}



	private void updateModel(Model model, HospitalContractAdmin hospitalContractAdmin) {
		Hospital hospital = hospitalService.findById(hospitalContractAdmin.getHospitalId());
		List<AMIUser> amiUsers = userService.findByHospitalIdAdmin(hospitalContractAdmin.getHospitalId());
		
		
		hospitalContractAdmin.setHospitalId(hospital.getId());
		hospitalContractAdmin.setAcronym(hospital.getAcronym());
		hospitalContractAdmin.setUnderContract(hospital.isUnderContract());
		hospitalContractAdmin.setPriority(hospital.getPriority());
		hospitalContractAdmin.setHospital(hospital);
		model.addAttribute("hospitalContractAdmin", hospitalContractAdmin);
		model.addAttribute("amiUsers", amiUsers);
	}
	
	
	@RequestMapping(value="/hospitalContractAdmin", method = RequestMethod.POST, params = { "cancelUpdateHospitalContract" })
	public String cancelUpdateHospitalContract(Model model)
	{
		return "redirect:hospitalAdmin";
	}
	
	
	@RequestMapping(value="/hospitalContractAdmin", method = RequestMethod.POST, params = { "saveHospital" })
	public String save(Model model, @ModelAttribute HospitalContractAdmin hospitalContractAdmin,@RequestParam long saveHospital)
	{
		if(hospitalContractAdmin.isUnderContract()){
			if(hospitalContractAdmin.getAcronym()==null || hospitalContractAdmin.getAcronym().length()==0){
				model.addAttribute("message", "You must provide an acronym for contract hospitals");
				return "hospitalContractAdmin";
			}
		}
		if(!hospitalContractAdmin.isUnderContract()){
			if(hospitalContractAdmin.getAcronym()!=null && hospitalContractAdmin.getAcronym().length()>0){
				model.addAttribute("message", "Acronym must be empty for non contract hospitals");
				return "hospitalContractAdmin";
			}
		}
		
		
		Hospital hospitalToSave = hospitalService.findById(hospitalContractAdmin.getHospitalId());
		hospitalToSave.setAcronym(hospitalContractAdmin.getAcronym());
		hospitalToSave.setUnderContract(hospitalContractAdmin.isUnderContract());
		
		updateModel(model, hospitalContractAdmin);
		hospitalService.save(hospitalToSave);
		
		
		if(hospitalContractAdmin.isUnderContract())
		{
			RequestSequence requestSequence =requestSequenceService.findByHospialId(hospitalContractAdmin.getHospitalId());
			if(requestSequence==null)
			{
				requestSequence = new RequestSequence();
				requestSequence.setHospitalId(hospitalToSave.getId());
				requestSequence.setHospitalName(hospitalToSave.getName());
				requestSequence.setNextNumber(1L);
				requestSequenceService.save(requestSequence);
			}
			
		}
		model.addAttribute("message", "Hospital Upated");
		
		return "hospitalContractAdmin";
	}
	
	@RequestMapping(value="/hospitalContractAdmin", method = RequestMethod.POST, params = { "updatePriority" })
	public String updatePriority(Model model, @ModelAttribute HospitalContractAdmin hospitalContractAdmin,@RequestParam long updatePriority)
	{
		
		Hospital hospitalToSave = hospitalService.findById(hospitalContractAdmin.getHospitalId());
		hospitalToSave.setPriority(hospitalContractAdmin.getPriority());
		hospitalService.save(hospitalToSave);
		updateModel(model, hospitalContractAdmin);
		model.addAttribute("message", "Hospital's Priority Upated");
		
		return "hospitalContractAdmin";
	}
	
	
	
	@RequestMapping(value="/hospitalContractAdmin", method = RequestMethod.POST, params ="deactivateUser")
	public String deactivateUser(Model model, @ModelAttribute HospitalContractAdmin hospitalContractAdmin, @RequestParam String deactivateUser)
	{
		AMIUser user = userService.findByUserAdmin(deactivateUser);
		if(user.isAccountActive())
		{
			user.setAccountActive(false);
		}
		else
		{
			user.setAccountActive(true);
		}
		userService.save(user);
		updateModel(model,hospitalContractAdmin);
		
		return "hospitalContractAdmin";
	}
	
	@RequestMapping(value="/hospitalContractAdmin", method = RequestMethod.GET, params ="userName")
	public String editAdminUser(Model model, @ModelAttribute HospitalContractAdmin hospitalContractAdmin,@RequestParam String userName)
	{
		AMIUser user = userService.findByUserAdmin(userName);
		
		
		return "hospitalContractAdmin";
	}
	
	
}
