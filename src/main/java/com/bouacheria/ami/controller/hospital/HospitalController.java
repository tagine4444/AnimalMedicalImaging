package com.bouacheria.ami.controller.hospital;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bouacheria.ami.controller.AbstractAmiController;
import com.bouacheria.ami.controller.ServiceRequestAndCaseHelper;
import com.bouacheria.ami.controller.bean.refdata.USStates;
import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.factory.UserFactory;
import com.bouacheria.ami.service.hospital.HospitalService;
import com.bouacheria.ami.service.users.UserService;

@Controller
public class HospitalController extends AbstractAmiController {

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private UserService amiUserService;

	@Autowired
	private UserFactory amiUserFactory;

	@Autowired
	private USStates usStates;
	
	@Autowired
	private ServiceRequestAndCaseHelper serviceRequestAndCaseHelper;
	

	@ModelAttribute("hospital")
	public Hospital createHospital() {
		return new Hospital();
	}

	@RequestMapping(value = "/hospital", method = RequestMethod.GET)
	public String goToHospital(Model model) {
		model.addAttribute("usStates", usStates.getUsStatesCodeList());
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		return "hospitalCreateAccount";
	}

	@RequestMapping(value = "/hospitalCreateAccount", method = RequestMethod.GET)
	public String goToHospitalCreateAccount(Model model,
			@ModelAttribute Hospital hospital) {
		serviceRequestAndCaseHelper.addRefDataToModel(model);
		model.addAttribute("usStates", usStates.getUsStatesCodeList());
		return "hospitalCreateAccount";
	}

	@RequestMapping(value = "/hospitalCreateAccount", method = RequestMethod.POST, params = { "saveHospital" })
	public String save(Model model, @Valid @ModelAttribute Hospital hospital,
			BindingResult result) {
		AMIUser anAmiUser = hospital.getAmiUser();

		if (result.hasErrors()) {
			anAmiUser.setPwd("");
			anAmiUser.setConfirmPassword("");
			return "hospitalCreateAccount";
		}

		hospitalService.save(hospital);

		amiUserFactory.initActiveVet(anAmiUser, hospital.getId());
		amiUserService.save(anAmiUser);
		
		// null out hospital that just signed up, just to make sure it ain't in the session..security reason
		hospital = null;
		return "redirect:hospitalCreateAccountConfirm";
	}
	
	@RequestMapping(value = "/hospitalCreateAccountConfirm", method = RequestMethod.GET)
	public String save(Model model) 
	{
		return "hospitalCreateAccountConfirm";
	}
}
