package com.bouacheria.ami.repository.datatype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.datatype.Species;

public interface SpeciesRepository extends JpaRepository<Species, Long>, JpaSpecificationExecutor<Species>
{
}
