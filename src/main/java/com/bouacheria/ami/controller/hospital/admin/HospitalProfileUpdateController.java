package com.bouacheria.ami.controller.hospital.admin;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.hospital.HospitalService;
import com.bouacheria.ami.service.users.UserService;

@Controller
public class HospitalProfileUpdateController extends AbstractAmiController{
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
	@Autowired
	private UserService userService;

	@Autowired
	private HospitalService hospitalService;
	
	
	@ModelAttribute("hospital")
	public Hospital createHospital()
	{
		return securityUtil.getHospitalForLoggedinUser();
	}

	@RequestMapping(value="/hospitalProfileUpdate", method = RequestMethod.GET)
	public String goToHospitalProfileUpdate(Model model, @ModelAttribute Hospital hospital)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		List<AMIUser> amiUsers = userService.findByHospitalId(hospital.getId());
		model.addAttribute("amiUsers", amiUsers);
		
		return "hospitalProfileUpdate";
	}
	
	
	
//	@RequestMapping(value="/hospitalProfileUpdate", method = RequestMethod.GET,  params={"addUser"})
//	public String addUser(Model model, @ModelAttribute Hospital hospital, String userName)
//	{
//		
//		return "hospitalUser";
//	}
	
	@RequestMapping(value="/hospitalProfileUpdate", method = RequestMethod.GET,  params={"userName", "delete"})
	public String deleteHosiptalUser(Model model, @ModelAttribute Hospital hospital, String userName)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		String loggedinUser = securityUtil.getLoggedinUserName();
		
		if(loggedinUser.equals(userName))
		{
			model.addAttribute("message", "You cannot delete your own user");
			List<AMIUser> amiUsers = userService.findByHospitalId(hospital.getId());
			model.addAttribute("amiUsers", amiUsers);
			return "hospitalProfileUpdate";
		}
		AMIUser amiUser = userService.findByUser(userName);
		amiUser.setDeleteTime(new DateTime());
		amiUser.setDeleteBy( loggedinUser);
		userService.save(amiUser);
		
		
		List<AMIUser> amiUsers = userService.findByHospitalId(hospital.getId());
		model.addAttribute("amiUsers", amiUsers);
		
		return "hospitalProfileUpdate";
	}
	
	
	@RequestMapping(value="/hospitalProfileUpdate", method = RequestMethod.POST,  params={"updateLab"})
	public String updateLab(Model model, @ModelAttribute Hospital hospital)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		Hospital hospitalToUpdate = hospitalService.findById(securityUtil.getHospitalForLoggedinUser().getId());
		hospitalToUpdate.setLab(hospital.getLab());
		hospitalToUpdate.setLabAccount(hospital.getLabAccount());
		hospitalToUpdate.setOffice(hospital.getOffice());
		hospitalToUpdate.setBackLine(hospital.getBackLine());
		hospitalToUpdate.setFax(hospital.getFax());
		
		hospitalService.save(hospitalToUpdate);
		
		List<AMIUser> amiUsers = userService.findByHospitalId(hospital.getId());
		model.addAttribute("amiUsers", amiUsers);
		model.addAttribute("message", "Hospital Updated");
		
		return "hospitalProfileUpdate";
	}
	
	
	
}
