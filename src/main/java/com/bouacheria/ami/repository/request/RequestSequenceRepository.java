package com.bouacheria.ami.repository.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.request.RequestSequence;

public interface RequestSequenceRepository extends JpaRepository<RequestSequence, Long>, JpaSpecificationExecutor<RequestSequence>
{
}
