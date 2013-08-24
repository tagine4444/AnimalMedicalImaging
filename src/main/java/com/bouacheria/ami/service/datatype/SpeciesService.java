package com.bouacheria.ami.service.datatype;

import java.util.List;

import com.bouacheria.ami.domain.datatype.Species;

public interface SpeciesService {

	List<Species> findAll();

	void save(Species species);

}
