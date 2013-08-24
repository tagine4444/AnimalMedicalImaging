package com.bouacheria.ami.repository.datatype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.datatype.Breed;

public interface BreedRepository extends JpaRepository<Breed, Long>, JpaSpecificationExecutor<Breed>
{
}
