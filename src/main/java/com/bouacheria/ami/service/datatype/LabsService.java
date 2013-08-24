package com.bouacheria.ami.service.datatype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.datatype.Labs;
import com.bouacheria.ami.repository.datatype.LabsRepository;

@Service
public class LabsService 
{
	@Autowired
	private LabsRepository repo;
	
	public List<Labs> findAll()
	{
		return repo.findAll();
	}
	
	@Transactional
	public Labs save(Labs labs)
	{
		return repo.save(labs);
	}
	
}
