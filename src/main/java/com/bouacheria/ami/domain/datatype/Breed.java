package com.bouacheria.ami.domain.datatype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "amischema", name = "BREED")
public class Breed {
	@Id
	@GeneratedValue
	protected Long id = null;
	
	@Column(name = "name", nullable = true, length = 255)
	private String name = null;

	@Column(name = "species", nullable = true, length = 255)
	private String species = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

}
