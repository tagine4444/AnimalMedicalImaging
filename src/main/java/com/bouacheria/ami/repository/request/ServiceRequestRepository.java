package com.bouacheria.ami.repository.request;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bouacheria.ami.domain.request.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long>, JpaSpecificationExecutor<ServiceRequest>
{
	ServiceRequest findByRequestNumber(String requestNumber);

	ServiceRequest findByRequestNumberAndHospitalId(String requestNumber, long hospitalId);

	
	@Query("select s from ServiceRequest s where s.stat = ?1 and s.priority =?2 and s.trascribedDate is null and s.readingInCompleteDate is null ORDER BY id")
	List<ServiceRequest> findRequestNotTranscribedAndNotRead(boolean stat, int priority);
	
	
	List<ServiceRequest> findByTrascribedDateIsNullAndStatAndHospitalId(boolean stat, long hospitalId);
	List<ServiceRequest> findByTrascribedDateIsNullAndHospitalId(long hospitalId);
	List<ServiceRequest> findByTrascribedDateIsNotNullAndStatAndHospitalId(boolean stat, long hospitalId);
	 
	
	@Query("select s from ServiceRequest s where s.stat = ?1 and s.trascribedDate >=curdate() ORDER BY id")
	List<ServiceRequest> findByTrascribedTodayAndStat(boolean stat);

	@Query("select s from ServiceRequest s where s.stat = ?1 ORDER BY id DESC")
	List<ServiceRequest> searchByStat(boolean stat);
	
	@Query("select s from ServiceRequest s where s.hospitalId = ?1 and requestDate>=curdate() ORDER BY id")
	List<ServiceRequest> findTodayRequestForHospital(long hospitalId);
	
	@Query("select s from ServiceRequest s where s.hospitalId = ?1 ORDER BY id DESC LIMIT 30")
	List<ServiceRequest> findLas30RequestForHospital(long hospitalId);

	@Query("select s from ServiceRequest s  ORDER BY id DESC LIMIT 60")
	List<ServiceRequest> findLas60Request();
	
	@Query("select s from ServiceRequest s where s.hospitalId = ?1 and stat = true ORDER BY id DESC LIMIT 30")
	List<ServiceRequest> findLas30STATRequestForHospital(long hospitalId);
	
	@Query("select s from ServiceRequest s where s.stat = true ORDER BY id DESC LIMIT 60")
	List<ServiceRequest> findLas60STATRequest();
	
	@Query("select s from ServiceRequest s where s.hospitalId = ?1 and requestDate>=?2 and requestDate<=?3 ORDER BY id DESC")
	List<ServiceRequest> findRequestForHospitalAndDateRange(long hospitalId,DateTime begDate, DateTime endDate );

	@Query("select s from ServiceRequest s where s.requestDate>=?1 and s.requestDate<=?2 ORDER BY id DESC")
	List<ServiceRequest> findRequestForDateRange(DateTime begDate, DateTime endDate );


	@Query("select s from ServiceRequest s , Case c where s.caseId = c.id and c.capturedInQuickBook =?1  ORDER BY s.id")
	List<ServiceRequest> findCaputredInQuickbook(boolean capturedInQuickBook);
	
//
//	@Query("select s from ServiceRequest s where s.hospitalId = ?1 and requestDate =?2 ")
//	List<ServiceRequest> findRequestForHospitalAndDateRange(long hospitalId,DateTime aDate);
}
