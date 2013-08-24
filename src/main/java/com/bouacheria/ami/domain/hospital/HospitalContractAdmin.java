package com.bouacheria.ami.domain.hospital;


public class HospitalContractAdmin {

	private boolean underContract;
	private String acronym;
	private long hospitalId;
	private int priority;
	private Hospital hospital;


	
	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public boolean isUnderContract() {
		return underContract;
	}

	public void setUnderContract(boolean underContract) {
		this.underContract = underContract;
	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

}
