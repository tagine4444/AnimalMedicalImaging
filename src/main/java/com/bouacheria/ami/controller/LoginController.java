package com.bouacheria.ami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.domain.user.UserLogin;
import com.bouacheria.ami.security.Permissions;
import com.bouacheria.ami.security.SecurityUtil;
import com.bouacheria.ami.service.amiservice.AmiServiceServiceCached;
import com.bouacheria.ami.temp.AmiInit;
import com.bouacheria.ami.temp.DeleteMeService;

@Controller
public class LoginController {

	//spring.profiles.active=local for local
	//no setting default is production
	//public final static boolean CLOUDBEES = false;
	
	

	
//	@Autowired
//	private DeleteMeService deleteMeService;
//	
//	@Autowired
//	private AmiServiceServiceCached serviceCache;
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private AmiInit amiInit;
	
	@ModelAttribute("userLogin")
	public UserLogin createUserLogin() 
	{
		return new UserLogin();
	}
	


	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String goToLogin(Model model)
	{
		amiInit.init();
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, params = "redirect")
	public String loginRedirect(ModelMap model)
	{
		if(securityUtil.isUserRole(Permissions.ROLE_ADMIN))
		{
			return "redirect:requestDashboard";
		}
		if(securityUtil.isUserRole(Permissions.ROLE_VET))
		{
			return "redirect:serviceRequest";
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, params = "login_error")
	public String loginerror(ModelMap model)
	{
		SecurityContextHolder.clearContext();
		model.addAttribute("error", Boolean.TRUE);
		model.addAttribute("errorMessage", "Invalid User/Password");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, params = "logout")
	public String logout(ModelMap model)
	{
		SecurityContextHolder.clearContext();
		return "login";
	}
	
}
