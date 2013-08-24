package com.bouacheria.ami.domain.contract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(schema = "amischema", name = "CONTRACT")
public class Contract {

	@Id
	@Column(name="ACRONYM", length=50 ,nullable = false)
	private String acronym;
	
	@Column(name="HOSPITAL_ID", nullable = false)
	private Long hospitalId = null;
	
	@Column(name="HOSPITAL_NAME", nullable = false)
    private String hospitalName = null;
	
	@Column(name="EFFECTIVE_DATE", nullable = false)
	private DateTime effectiveDate;
	
	@Column(name="EXPIRATION_DATE")
	private DateTime expirationDate;

	@Column(name="CONTRACT_VOID", nullable = false)
	private boolean contractVoid;
	

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public DateTime getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(DateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public DateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(DateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isContractVoid() {
		return contractVoid;
	}

	public void setContractVoid(boolean contractVoid) {
		this.contractVoid = contractVoid;
	}
	
	
	
}
