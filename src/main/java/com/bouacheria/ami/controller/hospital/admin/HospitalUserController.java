package com.bouacheria.ami.controller.hospital.admin;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.bouacheria.ami.factory.UserFactory;
import com.bouacheria.ami.security.Permissions;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.hospital.HospitalService;
import com.bouacheria.ami.service.users.UserService;

@Controller
public class HospitalUserController {
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HospitalService hospitalService;
	
	@Autowired
	private UserFactory userFactory;
	
	
	@ModelAttribute("amiUser")
	public AMIUser createAMIUser()
	{
		return new AMIUser();
	}

	@RequestMapping(value="/hospitalUser", method = RequestMethod.GET, params="addUser")
	public String addUser(Model model, @ModelAttribute AMIUser user, @RequestParam String addUser)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		Long hospitalId = securityUtil.getHospitalForLoggedinUser().getId();
		
		AMIUser amiUser = userFactory.getActiveVet("", "", hospitalId, "", "", "", "");
		model.addAttribute("newAmiUser", amiUser);
		
		return "hospitalUserNew";
	}
	@RequestMapping(value="/hospitalUser", method = RequestMethod.GET, params="editUser")
	public String goToHospitalUser(Model model, @ModelAttribute AMIUser user, @RequestParam String editUser)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		AMIUser amiUser = userService.findByUser(editUser);
		model.addAttribute("amiUser", amiUser);
		
		return "hospitalUser";
	}
	
	@RequestMapping(value="/hospitalUser", method = RequestMethod.POST, params={ "updateUser"})
	public String updateHosiptalUser(Model model,  @ModelAttribute AMIUser user)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		
		AMIUser amiUser = userService.findByUser(user.getUserName());
		List<String> errors = user.getErrorOnUserCreation(amiUser);
		
		boolean hasError =errors!=null && errors.size()>0;
		if(errors!=null && errors.size()>0)
		{
			StringBuilder sb = new StringBuilder();
			for (String error : errors) {
				sb.append("["+error + "] ");
			}
			model.addAttribute("message", sb.toString());
		}
		
		
//		boolean hasError = false;
		boolean isPwdChanged = !amiUser.getPwd().equals(user.getPwd());
		boolean isEmailChanged = !amiUser.getEmail().equals(user.getEmail());
//		
//		boolean isConfirmedPwdEntered   = StringUtils.isNotEmpty(user.getConfirmPassword());
//		boolean isConfirmedEmailEntered = StringUtils.isNotEmpty(user.getConfirmEmail());
//		
//		if(isPwdChanged)
//		{
//			if(!user.getPwd().equals(user.getConfirmPassword()))
//			{
//				model.addAttribute("message", "The password has been changed, and doesn't match the confimed password.");
//				hasError = true;
//			}
//		}
//		if(isConfirmedPwdEntered)
//		{
//			if(!user.getPwd().equals(user.getConfirmPassword()))
//			{
//				model.addAttribute("message", "The password and doesn't match the confimed password.");
//				hasError = true;
//			}
//		}
//		
//		if(isEmailChanged)
//		{
//			if(!user.getEmail().equals(user.getConfirmEmail()))
//			{
//				model.addAttribute("message", "The Email has been changed, and doesn't match the confimed Email.");
//				hasError = true;
//			}
//		}
//		if(isConfirmedEmailEntered)
//		{
//
//			if(!user.getEmail().equals(user.getConfirmEmail()))
//			{
//				model.addAttribute("message", "The Email doesn't match the confimed Email.");
//				hasError = true;
//			}
//		}
		
		if(hasError)
		{
			model.addAttribute("amiUser", amiUser);
			return "hospitalUser";
		}

		if(isPwdChanged)
		{
			amiUser.setPwd(user.getPwd());
		}
		if(isEmailChanged)
		{
			amiUser.setEmail(user.getEmail());
		}

		amiUser.setFirstName(user.getFirstName());
		amiUser.setLastName(user.getLastName());
		amiUser.setOccupation(user.getOccupation());
		
		
		
		Hospital hospital = hospitalService.findById(amiUser.getHospitalId());
		userService.save(amiUser);
		model.addAttribute("hospital", hospital);
		
		return "redirect:hospitalProfileUpdate";
	}
	@RequestMapping(value="/hospitalUser", method = RequestMethod.POST, params={ "saveNewUser"})
	public String saveNewUser(Model model,  @ModelAttribute AMIUser user)
	{
		Long hospitalId = securityUtil.getHospitalForLoggedinUser().getId();
		Hospital hospital = hospitalService.findById(hospitalId);
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		user.setHospitalId(hospitalId);
		user.setPermission(Permissions.ROLE_VET.name());
		user.setAccountActive(true);
		userService.save(user);
		model.addAttribute("hospital", hospital);
		
		return "redirect:hospitalProfileUpdate";
	}
	
	
}
