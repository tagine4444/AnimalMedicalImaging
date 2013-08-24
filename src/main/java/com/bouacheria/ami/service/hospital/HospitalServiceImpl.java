package com.bouacheria.ami.service.hospital;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.hospital.Hospital;
import com.bouacheria.ami.repository.hospital.HospitalRepository;

// bean declared in the spring-security-config.xml
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository repo;
	
	@Override
	@Transactional
	public Hospital save(Hospital hospital){
		return repo.save(hospital);
	}
	
	@Override
	public Hospital findById(Long id){
		return repo.findOne(id);
	}

	
	@Override
	public List<Hospital> findAll(){
		return repo.findAll();
	}

}
