package com.bouacheria.ami.domain.vet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "amischema", name = "Vet")
public class Vet {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	private VetAttribute vetAttribute;
	
	public Long getId() {
		return id;
	}

	public VetAttribute getVetAttribute() {
		return vetAttribute;
	}

	public void setVetAttribute(VetAttribute vetAttribute) {
		this.vetAttribute = vetAttribute;
	}
	
	
	
}
