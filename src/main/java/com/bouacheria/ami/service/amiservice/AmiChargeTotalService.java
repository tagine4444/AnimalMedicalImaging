package com.bouacheria.ami.service.amiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.amiservices.AmiChargeTotal;
import com.bouacheria.ami.repository.amiservices.AmiChargeTotalRepository;
@Service
public class AmiChargeTotalService {

	@Autowired
	private AmiChargeTotalRepository repo;
	
	public List<AmiChargeTotal> findAll()
	{
		return repo.findAll();
	}

	@Transactional
	public AmiChargeTotal save(AmiChargeTotal amiChargeTotal) {
		return repo.save(amiChargeTotal);
	}
	
	public AmiChargeTotal findById(long id) {
		return repo.findOne(id);
	}
	
	public AmiChargeTotal findByAmiCaseId(long amiCaseId)
	{
		return repo.findByAmiCaseId(amiCaseId);
	}

}
