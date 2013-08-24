package com.bouacheria.ami.domain.request;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class SearchRequest {

	private Long hospitalId= -1L;
	private String requestNumber;
	private DateTime begDate;
	private DateTime endDate;
	private boolean stat = false;
	

	public SearchRequest() {

	}

	public boolean isStat() {
		return stat;
	}

	public void setStat(boolean stat) {
		this.stat = stat;
	}

	public void setHospitalId(Long hospitalId) {
		// TODO Auto-generated method stub
		
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	@DateTimeFormat(pattern="MM/dd/yyyy")
	public DateTime getBegDate() {
		return begDate;
	}

	public void setBegDate(DateTime begDate) {
		this.begDate = begDate;
	}
 
	@DateTimeFormat(pattern="MM/dd/yyyy")
	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

}
