package com.bouacheria.ami.domain.client;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "amischema", name = "CLIENT")
public class Client {
	@Id
	@GeneratedValue
	protected Long id = null;
	
	@Embedded
	private ClientAttribute clientAttribute = null;
	
	
	public Long getId() {
		return id;
	}


	public ClientAttribute getClientAttribute() {
		return clientAttribute;
	}


	public void setClientAttribute(ClientAttribute clientAttribute) {
		this.clientAttribute = clientAttribute;
	}

	
}
