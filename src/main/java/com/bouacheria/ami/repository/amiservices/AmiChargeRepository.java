package com.bouacheria.ami.repository.amiservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.amiservices.AmiCharge;

public interface AmiChargeRepository extends JpaRepository<AmiCharge, Long>, JpaSpecificationExecutor<AmiCharge>
{
	List<AmiCharge>findByAmiCaseId(long amiCaseId);
	AmiCharge findByAmiCaseIdAndCategory(long amiCaseId, String category);
}