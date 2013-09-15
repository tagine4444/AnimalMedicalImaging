package com.bouacheria.ami.domain.datatype;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Weight {

	@NotBlank
	@Column(name="WEIGHT")
	private String value;
	
	@NotEmpty
	@Column(name="UOM")
	private String uom;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
 
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
}
