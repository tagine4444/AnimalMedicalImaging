package com.bouacheria.ami.repository.datatype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.datatype.Labs;

public interface LabsRepository extends JpaRepository<Labs, Long>, JpaSpecificationExecutor<Labs>
{
}
