package com.bouacheria.ami.service.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.request.RequestSequence;
import com.bouacheria.ami.repository.request.RequestSequenceRepository;

@Service
public class RequestSequenceService {
	
	@Autowired
	private RequestSequenceRepository repo;

	 
	public RequestSequence save(RequestSequence requestSequence)
	{
		return repo.save(requestSequence);
	}
	
	public RequestSequence findByHospialId(long id)
	{
		return repo.findOne(id);
	}
}
