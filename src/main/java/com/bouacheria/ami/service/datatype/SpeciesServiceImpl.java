package com.bouacheria.ami.service.datatype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.datatype.Species;
import com.bouacheria.ami.repository.datatype.SpeciesRepository;

@Service
public class SpeciesServiceImpl implements SpeciesService{

	@Autowired
	private SpeciesRepository repo;
	
	
	@Override
	public List<Species> findAll()
	{
		return repo.findAll();
	}
	
	@Override
	public void save(Species species)
	{
		repo.save(species);
	}
}
