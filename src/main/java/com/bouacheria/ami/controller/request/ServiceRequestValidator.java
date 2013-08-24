package com.bouacheria.ami.controller.request;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bouacheria.ami.domain.request.ServiceRequest;


public class ServiceRequestValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		ServiceRequest sr = (ServiceRequest)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors, sr.getClientFirstName() , "required.username");
		
	}

}
