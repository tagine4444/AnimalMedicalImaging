package com.bouacheria.ami.domain.pet;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.bouacheria.ami.domain.datatype.Weight;

@Embeddable
public class PetAttribute 
{

	@NotBlank(message="Patient Name may not be empty")
	@Column(name="PET_NAME", length=200)
	private String petName  = null;
	
	@NotBlank(message="Patient Sex may not be empty")
	@Column(name="SEX", length=10)
	private String sex = null;

	@Column(name="SPECIES", length=255)
	private String species = null;
	
	// lab, pitbull etc..
	@NotEmpty(message="may not be empty")
	@Column(name="BREED",length=255)
	private String breed = null;
	
	@NotEmpty(message="Age may not be empty")
	@Column(name="AGE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime dob = null;
	
	@Column(name="PATIENT_AGE", length=99)
	private String patientAge = null;
	
	@Embedded
	private Weight weight = null;
	
	public PetAttribute() {
		this.weight = new Weight();
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String name) {
		this.petName = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public DateTime getDob() {
		return dob;
	}

	public void setDob(DateTime dob) {
		this.dob = dob;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	
}
