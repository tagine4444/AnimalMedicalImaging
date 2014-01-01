package com.bouacheria.ami.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.bouacheria.ami.repository.config.ConfigUtil;

/**
 * Handles requests for the application home page.
 */
//@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired private ConfigUtil configUtil;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
//		System.out.println(configUtil.toString());
//		
//		logger.info(configUtil.toString());
		return "redirect:login";
	}
	
}
