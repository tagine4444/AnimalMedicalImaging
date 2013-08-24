package com.bouacheria.ami.service.amiservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.amiservices.AmiFee;
import com.bouacheria.ami.repository.amiservices.AmiFeeRepository;
@Service
public class AmiFeeService {

	@Autowired
	private AmiFeeRepository repo;
	
	public List<AmiFee> findAll()
	{
		return repo.findAll();
	}

	@Transactional
	public AmiFee save(AmiFee amiService) {
		return repo.save(amiService);
	}
	
	public AmiFee findById(long id) {
		return repo.findOne(id);
	}
	
	public AmiFee findByCategory(String category) {
		return repo.findByCategory(category);
	}
	
}
