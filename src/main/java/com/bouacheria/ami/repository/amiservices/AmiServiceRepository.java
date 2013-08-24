package com.bouacheria.ami.repository.amiservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.amiservices.AmiService;

public interface AmiServiceRepository extends JpaRepository<AmiService, Long>, JpaSpecificationExecutor<AmiService>
{
	
	AmiService findByIdAndDeactivatedDateIsNull(long id);
	List<AmiService> findByDeactivatedDateIsNull();
}