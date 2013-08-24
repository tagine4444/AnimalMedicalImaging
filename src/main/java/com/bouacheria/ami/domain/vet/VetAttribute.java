package com.bouacheria.ami.domain.vet;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.bouacheria.ami.domain.datatype.Contact;

@Embeddable
public class VetAttribute {
	
	public final static String SEPARATOR = "VET_";
	private final static String EMAIL 	 = SEPARATOR + Contact.EMAIL_COL ;
	private final static String CELL 	 = SEPARATOR + Contact.CELL_COL;
	private final static String OFFICE 	 = SEPARATOR + Contact.OFFICE_COL;
	private final static String BACKLINE  = SEPARATOR + Contact.BACKLINE_COL;
	private final static String FAX 	 	 = SEPARATOR + Contact.FAX_COL;
	
	
	
	
	@Column(name="FIRST_NAME", nullable = true , length=200)
	private String firstName = null;
	
	@Column(name="LAST_NAME", nullable = true , length=200)
	private String lastName = null;
	

	@Embedded
	@AttributeOverrides({
	        @AttributeOverride(name=Contact.CONTACT_EMAIL, column=@Column(name=EMAIL )),
	        @AttributeOverride(name=Contact.CONTACT_CELL, column=@Column(name=CELL)),
	        @AttributeOverride(name=Contact.CONTACT_OFFICE, column=@Column(name=OFFICE)),
	        @AttributeOverride(name=Contact.CONTACT_BACKLINE, column=@Column(name=BACKLINE)),
	        @AttributeOverride(name=Contact.CONTACT_FAX, column=@Column(name=FAX))
	        
	    })
	private Contact contact = null;
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
