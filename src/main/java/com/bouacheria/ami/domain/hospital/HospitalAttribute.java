package com.bouacheria.ami.domain.hospital;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.bouacheria.ami.domain.datatype.Address;
import com.bouacheria.ami.domain.datatype.Contact;

@Embeddable
public class HospitalAttribute {
	
	public final static String SEPARATOR 		= "HOSP_";
	private final static String EMAIL 	 		= SEPARATOR + Contact.EMAIL_COL ;
	private final static String CELL 	 		= SEPARATOR + Contact.CELL_COL;
	private final static String OFFICE 	 		= SEPARATOR + Contact.OFFICE_COL;
	private final static String BACKLINE 		= SEPARATOR + Contact.BACKLINE_COL;
	private final static String FAX 	 			= SEPARATOR + Contact.FAX_COL;
	
	private final static String ADRESS 	 		= SEPARATOR + Address.ADRESS_COL;
	private final static String ADDRESS_CITY 	= SEPARATOR + Address.CITY_COL;
	private final static String ADDRESS_ZIP 	= SEPARATOR + Address.ZIP_COL;
	private final static String STATE_PROVINCE 	= SEPARATOR + Address.STATE_PROVINCE_COL;
	private final static String COUNTRY 		= SEPARATOR + Address.COUNTRY_COL;
	
	@Column(name="HOSPITAL_NAME", nullable = true, length=300)
    private String hospitalName = null;
	
	@Column(name="ACRONYM", length=50, nullable = true)
	private String acronym="";
	
	@Embedded
	@AttributeOverrides({
	        @AttributeOverride(name=Address.ADRESS, column=@Column(name=ADRESS)),
	        @AttributeOverride(name=Address.ADDRESS_CITY, column=@Column(name=ADDRESS_CITY)),
	        @AttributeOverride(name=Address.ADDRESS_ZIP, column=@Column(name=ADDRESS_ZIP)),
	        @AttributeOverride(name=Address.ADDRESS_STATE_PROVINCE, column=@Column(name=STATE_PROVINCE)),
	        @AttributeOverride(name=Address.ADDRESS_COUNTRY, column=@Column(name=COUNTRY))
	    })
	private Address address = null;
	
	
	@Embedded
	@AttributeOverrides({
	        @AttributeOverride(name=Contact.CONTACT_EMAIL, column=@Column(name=EMAIL )),
	        @AttributeOverride(name=Contact.CONTACT_CELL, column=@Column(name=CELL)),
	        @AttributeOverride(name=Contact.CONTACT_OFFICE, column=@Column(name=OFFICE)),
	        @AttributeOverride(name=Contact.CONTACT_BACKLINE, column=@Column(name=BACKLINE)),
	        @AttributeOverride(name=Contact.CONTACT_FAX, column=@Column(name=FAX))
	        
	    })
	private Contact contact = null;
	
	@Column(name="LAB", nullable = true, length=100)
    private String lab = null;
	
	@Column(name="LAB_ACCOUNT", nullable = true, length=100)
	private String labAccount = null;
	
	public HospitalAttribute() {
		this.contact = new Contact();
		this.address = new Address();
	}
	
	
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getLabAccount() {
		return labAccount;
	}

	public void setLabAccount(String labAccount) {
		this.labAccount = labAccount;
	}


	public String getAcronym() {
		return acronym;
	}


	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
}
