package com.bouacheria.ami.controller.validations;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bouacheria.ami.domain.hospital.Hospital;

public class AnimalValidator implements Validator {

	@Override
	public void validate(Object target, Errors errors) 
	{
//		ValidationUtils.rejectIfEmptyOrWhitespace(
//			errors, "pwd", "required.fistName");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Hospital.class.isAssignableFrom(clazz);
	}
}
