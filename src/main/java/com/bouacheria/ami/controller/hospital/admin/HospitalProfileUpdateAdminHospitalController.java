package com.bouacheria.ami.controller.hospital.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.datatype.DataTypeService;
import com.bouacheria.ami.service.hospital.HospitalService;

@Controller
public class HospitalProfileUpdateAdminHospitalController {
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
//	@Autowired
//	private UserService userService;

	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private DataTypeService dataTypeService;
	
	
	@ModelAttribute("hospital")
	public Hospital createHospital()
	{
		return new Hospital();
	}

	
	@RequestMapping(value="/hospitalProfileUpdateAdminHospital", method = RequestMethod.GET, params ="hospitalId")
	public String updateHospital(Model model, @ModelAttribute Hospital hospital, @RequestParam long hospitalId)
	{
		hospital = hospitalService.findById(hospitalId);
		
		model.addAttribute("amiUser", new AMIUser());
		model.addAttribute("labsList", dataTypeService.getLabsList());
		model.addAttribute("hospital", hospital);
		
		//serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		return "hospitalProfileUpdateAdminHospital";
	}
	
		
	
	@RequestMapping(value="/hospitalProfileUpdateAdminHospital", method = RequestMethod.POST,  params="updateHospital")
	public String updateLab(Model model, @ModelAttribute Hospital hospital, @RequestParam long updateHospital)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		
		
		Hospital hospitalToUpdate = hospitalService.findById(updateHospital);
		hospital.clone(hospitalToUpdate);
		
		hospitalService.save(hospitalToUpdate);
		
		model.addAttribute("message", "Hospital Updated");
		
		return "redirect:hospitalContractAdmin?updateContract="+hospitalToUpdate.getId();
	}
	
	
	
}
