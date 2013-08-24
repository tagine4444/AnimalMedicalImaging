package com.bouacheria.ami.domain.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(schema = "amischema", name = "REQUEST_SEQUENCE")
public class RequestSequence {

	@Id
	@Column(name="HOSPITAL_ID", nullable = false)
	private Long hospitalId;
	
	@NotEmpty
	@Column(name="HOSPITAL_NAME", length=200, nullable = false)
	private String hospitalName;
	
	@Column(name="NEXT_NUMBER", nullable = false)
	private Long nextNumber;

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

	public Long getNextNumber() {
		return nextNumber;
	}

	public void setNextNumber(Long nextNumber) {
		this.nextNumber = nextNumber;
	}
}
