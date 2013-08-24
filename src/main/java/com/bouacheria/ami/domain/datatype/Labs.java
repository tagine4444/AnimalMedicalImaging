package com.bouacheria.ami.domain.datatype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "amischema", name = "LABS")
public class Labs {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	
	@Column(name="name", nullable = true , length=500)
	private String name = null;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
