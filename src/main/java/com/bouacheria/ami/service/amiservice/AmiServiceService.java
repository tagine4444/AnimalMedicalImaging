package com.bouacheria.ami.service.amiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.repository.amiservices.AmiServiceRepository;
@Service
public class AmiServiceService 
{

	@Autowired
	private AmiServiceRepository repo;
	
	public List<AmiService> findAll()
	{
		//return repo.findAll();
		return repo.findByDeactivatedDateIsNull();
	}

	@Transactional
	public AmiService save(AmiService amiService) {
		return repo.save(amiService);
	}
	
	public AmiService findById(long id) {
		//return repo.findOne(id);
		return repo.findByIdAndDeactivatedDateIsNull(id);
	}
	
}
