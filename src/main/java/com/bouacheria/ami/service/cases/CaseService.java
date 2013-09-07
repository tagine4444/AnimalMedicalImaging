package com.bouacheria.ami.service.cases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.cases.Case;
import com.bouacheria.ami.repository.cases.CaseRepository;

@Service
public class CaseService {

   
	@Autowired
	private CaseRepository repo;
	
	public List<Case> findAll()
	{
		return repo.findAll();
	}

	@Transactional
	public Case save(Case Case) {
		return repo.save(Case);
	}
	
	public Case findById(long id) {
		return repo.findOne(id);
	}
	
	public List<Case> findSTATCasesToTranscribe() {
		return repo.findNonTranscribedCasesThatAreStat();
	}
	
	public List<Case> findCasesToTranscribe(int priority) {
		return repo.findNonTranscribedCasesThatAreNotStat(priority);
	}
	
	
	public List<Case> findSTATCasesTranscribedByHospital(long hospitalId) {
		return repo.findByTranscriptionCompleteDateIsNotNullAndReadingInCompleteDateIsNotNullAndHospitalIdAndStat(hospitalId,true);
	}
	
	public List<Case> findCasesTranscribedByHospital(long hospitalId) {
		return repo.findByTranscriptionCompleteDateIsNotNullAndReadingInCompleteDateIsNotNullAndHospitalIdAndStat(hospitalId,false);
	}

	
}
