package com.bouacheria.ami.domain.datatype;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Weight {

	@NotEmpty
	@Column(name="WEIGHT")
	private double value;
	
	@NotEmpty
	@Column(name="UOM")
	private String uom;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
 
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
}
