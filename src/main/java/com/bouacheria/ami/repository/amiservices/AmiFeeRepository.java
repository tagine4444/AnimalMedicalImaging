package com.bouacheria.ami.repository.amiservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.amiservices.AmiFee;

public interface AmiFeeRepository extends JpaRepository<AmiFee, Long>, JpaSpecificationExecutor<AmiFee>
{ 
	AmiFee findByCategory(String category);
}