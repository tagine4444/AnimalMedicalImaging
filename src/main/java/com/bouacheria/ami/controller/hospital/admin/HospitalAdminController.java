package com.bouacheria.ami.controller.hospital.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.service.hospital.HospitalService;

@Controller
public class HospitalAdminController extends AbstractAmiController{


	@Autowired
	private HospitalService hospitalService;
	
	
	
	@RequestMapping(value="/hospitalAdmin", method = RequestMethod.GET)
	public String goToHosiptalContractAdmin(Model model)
	{
		List<Hospital> hospitalList = hospitalService.findAll();
		
		model.addAttribute("hospitalList", hospitalList);
		
		return "hospitalAdmin";
	}
	
	
}
