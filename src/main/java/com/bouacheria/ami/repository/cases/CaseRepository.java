package com.bouacheria.ami.repository.cases;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bouacheria.ami.domain.cases.Case;

public interface CaseRepository extends JpaRepository<Case, Long>, JpaSpecificationExecutor<Case>
{
	
//	@Query("update AMICASE s set s.capturedInQuickBook =?1 where s.id>=?2 ")
//	List<ServiceRequest> updateCapturedInQuickBook(boolean capturedInQuickBook, long caseId);
//	
	
	
	//List<ServiceRequest> findRequestNotTranscribedAndNotRead(boolean stat, int priority);
	
	
	@Query("select s from Case s where s.stat is false and s.priority =?1 and s.transcriptionCompleteDate is null and s.readingInCompleteDate is not null  ORDER BY id")
	List<Case> findNonTranscribedCasesThatAreNotStat(int priority);
	
	@Query("select s from Case s where s.stat is true  and s.transcriptionCompleteDate is null and s.readingInCompleteDate is null ORDER BY id")
	List<Case> findNonTranscribedCasesThatAreStat();
	
	
	List<Case> findByTranscriptionCompleteDateIsNotNullAndReadingInCompleteDateIsNotNullAndHospitalIdAndStat(long hospitalId,boolean stat);
}
