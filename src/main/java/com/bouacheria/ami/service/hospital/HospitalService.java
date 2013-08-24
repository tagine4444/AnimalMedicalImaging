package com.bouacheria.ami.service.hospital;

import java.util.List;

import com.bouacheria.ami.domain.hospital.Hospital;

public interface HospitalService {

	Hospital save(Hospital hospital);

	Hospital findById(Long id);

	List<Hospital> findAll();
}
 