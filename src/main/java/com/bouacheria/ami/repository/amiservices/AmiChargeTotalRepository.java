package com.bouacheria.ami.repository.amiservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.amiservices.AmiChargeTotal;

public interface AmiChargeTotalRepository extends JpaRepository<AmiChargeTotal, Long>, JpaSpecificationExecutor<AmiChargeTotal>
{
	AmiChargeTotal findByAmiCaseId(long amiCaseId);
}