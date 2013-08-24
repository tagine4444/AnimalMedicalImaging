package com.bouacheria.ami.domain.pet;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "amischema", name = "Pet")
public class Pet {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	@Embedded
	private PetAttribute petAttribute;
	
	public Pet() {
		this.petAttribute = new PetAttribute();
	}

	public PetAttribute getPetAttribute() {
		return petAttribute;
	}

	public void setPetAttribute(PetAttribute petAttribute) {
		this.petAttribute = petAttribute;
	}
}
