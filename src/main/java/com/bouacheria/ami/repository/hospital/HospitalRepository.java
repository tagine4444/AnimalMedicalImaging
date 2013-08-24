package com.bouacheria.ami.repository.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bouacheria.ami.domain.hospital.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long>, JpaSpecificationExecutor<Hospital>
{
}
