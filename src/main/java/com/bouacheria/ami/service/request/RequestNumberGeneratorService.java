package com.bouacheria.ami.service.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.request.RequestSequence;
import com.bouacheria.ami.security.SecurityUtil;

@Service
public class RequestNumberGeneratorService {

	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private RequestSequenceService requestSequenceService;
	
	public String getRequestNumber(Long hospitalId, String hopitalAcronym)
	{
		RequestSequence requestSequence = requestSequenceService.findByHospialId(hospitalId);
		Long currentNumber = requestSequence.getNextNumber();
		long nextNumber = currentNumber + 1;
		requestSequence.setNextNumber(nextNumber);
		requestSequenceService.save(requestSequence);
		
		return hopitalAcronym + nextNumber;
		
	}
}
