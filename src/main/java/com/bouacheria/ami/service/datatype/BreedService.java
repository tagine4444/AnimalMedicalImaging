package com.bouacheria.ami.service.datatype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.datatype.Breed;
import com.bouacheria.ami.repository.datatype.BreedRepository;

@Service
public class BreedService {

	@Autowired
	private BreedRepository repo;
	
	public List<Breed> findAll()
	{
		return repo.findAll();
	}
	
	@Transactional
	public Breed save(Breed breed)
	{
		return repo.save(breed);
	}
}
