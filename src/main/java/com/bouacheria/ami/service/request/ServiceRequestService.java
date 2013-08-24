package com.bouacheria.ami.service.request;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bouacheria.ami.domain.request.ServiceRequest;
import com.bouacheria.ami.repository.request.ServiceRequestRepository;

@Service
public class ServiceRequestService  {

	@Autowired
	private ServiceRequestRepository repo;
	
	public List<ServiceRequest> findAll()
	{
		return repo.findAll();
	}

	@Transactional
	public ServiceRequest save(ServiceRequest serviceRequest) {
		return repo.save(serviceRequest);
	}
	
	
	public ServiceRequest findById(long id) {
		return repo.findOne(id);
	}
	
	// --------------------- Request that are in progress (not yet transcribed) for a hospital STAT & NORMAL-----------------------
	public List<ServiceRequest> findStatNotYetReadYet(long hospitalId)
	{
		return repo.findByTrascribedDateIsNullAndStatAndHospitalId(true, hospitalId);
	}
	public List<ServiceRequest> findNonStatNotYetReadYet(long hospitalId)
	{
		return repo.findByTrascribedDateIsNullAndStatAndHospitalId(false,hospitalId);
	}
	public List<ServiceRequest> findAllReqNotYetReadYet(long hospitalId)
	{
		return repo.findByTrascribedDateIsNullAndHospitalId(hospitalId);
	}
	
	
	
	// --------------------- Request Transcribed today for a hospital STAT & NORMAL-----------------------
	public List<ServiceRequest> findStatReqTranscribedTodayForHospital(long hospitalId)
	{
		return repo.findByTrascribedDateIsNotNullAndStatAndHospitalId(true, hospitalId);
	}
	public List<ServiceRequest> findReqTranscribedTodayForHospital(long hospitalId)
	{
		return repo.findByTrascribedDateIsNotNullAndStatAndHospitalId(false,hospitalId);
	}
	
	// --------------------- Request Transcribed today for a hospital STAT & NORMAL-----------------------
	public List<ServiceRequest> findStatReqTranscribedTodayForAMI()
	{ 
		List<ServiceRequest> result =  repo.findByTrascribedTodayAndStat(true);
		if(result ==null)
		{
			return new ArrayList<ServiceRequest>();
		}
		return result; 
	}
	public List<ServiceRequest> findReqTranscribedTodayForAMI()
	{
		List<ServiceRequest> result =  repo.findByTrascribedTodayAndStat(false);
		if(result ==null)
		{
			return new ArrayList<ServiceRequest>();
		}
		return result; 
	}
	
	// --------------------- Request List that Radiologist has to read STAT & NORMAL -----------------------
	public List<ServiceRequest> findStatToReadByRadiologist()
	{
		List<ServiceRequest> result = repo.findRequestNotTranscribedAndNotRead(true,ServiceRequest.PRIORITY_NORMAL);
		if(result ==null)
		{
			return new ArrayList<ServiceRequest>();
		}
		return result; 
	}
	
	public List<ServiceRequest> findNonStatToReadByRadiologist()
	{
		List<ServiceRequest> result =repo.findRequestNotTranscribedAndNotRead(false, ServiceRequest.PRIORITY_NORMAL);
		if(result ==null)
		{
			return new ArrayList<ServiceRequest>();
		}
		return result;
	}
	
	public List<ServiceRequest> findNonStatToReadByRadiologistHighPriority()
	{
		List<ServiceRequest> result = repo.findRequestNotTranscribedAndNotRead(false, ServiceRequest.PRIORITY_HIGH);
		if(result ==null)
		{
			return new ArrayList<ServiceRequest>();
		}
		return result;
	}
	
	
	public List<ServiceRequest> searchByStat(boolean stat)
	{
		return repo.searchByStat(stat);
	}
	
	public List<ServiceRequest> findTodayRequestForHospital(long hospitalId)
	{
		return repo.findTodayRequestForHospital(hospitalId);
	}
	
	
	public List<ServiceRequest> findLas30RequestForHospital(long hospitalId)
	{
		return repo.findLas30RequestForHospital(hospitalId);
	}
	
	
	public List<ServiceRequest> findLas30STATRequestForHospital(long hospitalId)
	{
		return repo.findLas30STATRequestForHospital(hospitalId);
	}
	
	
	public List<ServiceRequest> findRequestForHospitalAndDate(long hospitalId, DateTime aBegDate, DateTime anEnDate)
	{
		return repo.findRequestForHospitalAndDateRange(hospitalId,aBegDate,anEnDate);
	}
	
	
	public List<ServiceRequest> findRequestForDateRange( DateTime aBegDate, DateTime anEnDate)
	{
		return repo.findRequestForDateRange(aBegDate,anEnDate);
	}
	
	
	public ServiceRequest findByRequestNumberAndHospital(String requestNumber, long hospitalId)
	{
		return repo.findByRequestNumberAndHospitalId( requestNumber,hospitalId);
	}
	
	
	public List<ServiceRequest> findLas60Request()
	{
		return repo.findLas60Request();
	}
	
	public List<ServiceRequest> findLas60STATRequest()
	{
		return repo.findLas60STATRequest();
	}
	
	public List<ServiceRequest> findNotInQuickbook()
	{
		return repo.findCaputredInQuickbook(false);
	}
	
	public ServiceRequest findByRequestNumber(String requestNumber)
	{
		return repo.findByRequestNumber(requestNumber);
	}
	
}
