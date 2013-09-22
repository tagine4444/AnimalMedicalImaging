package com.bouacheria.ami.controller.hospital.admin;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;
import com.bouacheria.ami.service.users.UserService;

@Controller
public class HospitalProfileUpdateAdminUserController extends AbstractAmiController{
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AmiServiceServiceCached amiServiceCache;

//	@Autowired
//	private HospitalService hospitalService;
	
	
	@ModelAttribute("amiUser")
	public AMIUser createHospital()
	{
		return new AMIUser();
	}

	@RequestMapping(value="/hospitalProfileUpdateAdminUser", method = RequestMethod.GET, params ={"userName","hospitalId"})
	public String goToHospitalProfileUpdateAdminUser(Model model, @ModelAttribute AMIUser amiUser, @RequestParam String userName, @RequestParam long hospitalId)
	{
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		amiUser = userService.findByUserAdmin(userName);
		model.addAttribute("amiUser", amiUser);
		
		
		return "hospitalProfileUpdateAdminUser";
	}
	
	@RequestMapping(value="/hospitalProfileUpdateAdminUser", method = RequestMethod.POST, params ={"updateUser"})
	public String updateUser(Model model, @ModelAttribute AMIUser amiUser, @RequestParam String userName, @RequestParam long hospitalId)
	{
		
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		AMIUser amiUserDb = userService.findByUserAdmin(userName);
		
		List<String> errors = amiUser.getErrorOnUserCreation(amiUserDb);
		boolean hasError =errors!=null && errors.size()>0;
		if(errors!=null && errors.size()>0)
		{
			StringBuilder sb = new StringBuilder();
			for (String error : errors) {
				sb.append("["+error + "] ");
			}
			model.addAttribute("message", sb.toString());
		}
		
		model.addAttribute("amiUser", amiUser);
		
		if(hasError)
		{
			return "hospitalProfileUpdateAdminUser";
		}
		
		// make sure we set the permission back
		amiUser.setPermission(amiUserDb.getPermission());
		userService.save(amiUser);
		amiServiceCache.init();
		return "redirect:hospitalContractAdmin?updateContract="+hospitalId;
	}
	
	
	
	@RequestMapping(value="/hospitalProfileUpdateAdminUser", method = RequestMethod.GET,  params={"userName", "delete"})
	public String deleteHosiptalUser(Model model, @ModelAttribute Hospital hospital, String userName)
	{
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		String loggedinUser = securityUtil.getLoggedinUserName();
		
		if(loggedinUser.equals(userName))
		{
			model.addAttribute("message", "You cannot delete your own user");
			List<AMIUser> amiUsers = userService.findByHospitalId(hospital.getId());
			model.addAttribute("amiUsers", amiUsers);
			return "hospitalProfileUpdateAdminUser";
		}
		AMIUser amiUser = userService.findByUser(userName);
		amiUser.setDeleteTime(new DateTime());
		amiUser.setDeleteBy( loggedinUser);
		userService.save(amiUser);
		
		
		List<AMIUser> amiUsers = userService.findByHospitalId(hospital.getId());
		model.addAttribute("amiUsers", amiUsers);
		
		return "hospitalProfileUpdateAdminUser";
	}
	
	
	
}
