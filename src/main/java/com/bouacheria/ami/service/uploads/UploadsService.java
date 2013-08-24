package com.bouacheria.ami.service.uploads;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.uploads.Uploads;
import com.bouacheria.ami.repository.uploads.UploadsRepository;

@Service
public class UploadsService {

	@Autowired
	private UploadsRepository repo;
	
	@Transactional
	public Uploads save(Uploads Uploads){
		return repo.save(Uploads);
	}
	
	public Uploads findById(Long id){
		return repo.findOne(id);
	}
	
	public List<Uploads> findByRequestId(Long requestId){
		return repo.findByRequestId(requestId);
	}

	
	public List<Uploads> findAll(){
		return repo.findAll();
	}
}
