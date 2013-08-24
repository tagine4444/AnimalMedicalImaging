package com.bouacheria.ami.domain.amiservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "amischema", name = "AMI_CHARGE_TOTAL")
public class AmiChargeTotal {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	@Column(name="ami_case_id")
	private Long amiCaseId = null;
	
	@Column(name="price")
	private double price= 0;
	
	@Column(name="ami_charges_id",length=1000)
	private String amiChargesId = null;

	public Long getId() {
		return id;
	}

	public Long getAmiCaseId() {
		return amiCaseId;
	}


	public void setAmiCaseId(Long amiCaseId) {
		this.amiCaseId = amiCaseId;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAmiChargesId() {
		return amiChargesId;
	}

	public void setAmiChargesId(String amiChargesId) {
		this.amiChargesId = amiChargesId;
	}

}
