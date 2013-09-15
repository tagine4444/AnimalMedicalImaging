package com.bouacheria.ami.domain.client;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.bouacheria.ami.domain.datatype.Address;
import com.bouacheria.ami.domain.datatype.Contact;

@Embeddable
public class ClientAttribute {
	
	public final static String SEPARATOR = "CLIENT_";
	private final static String EMAIL 	 		= SEPARATOR + Contact.EMAIL_COL ;
	private final static String CELL 	 		= SEPARATOR + Contact.CELL_COL;
	private final static String HOME_PHONE 	 		= SEPARATOR + Contact.HOMEPHONE_COL;
	private final static String OFFICE 	 		= SEPARATOR + Contact.OFFICE_COL;
	private final static String BACKLINE 		= SEPARATOR + Contact.BACKLINE_COL;
	private final static String FAX 	 			= SEPARATOR + Contact.FAX_COL;
	
	private final static String ADRESS 	 		= SEPARATOR + Address.ADRESS_COL;
	private final static String ADDRESS_CITY 	= SEPARATOR + Address.CITY_COL;
	private final static String ADDRESS_ZIP 		= SEPARATOR + Address.ZIP_COL;
	private final static String STATE_PROVINCE 	= SEPARATOR + Address.STATE_PROVINCE_COL;
	private final static String COUNTRY 	= SEPARATOR + Address.COUNTRY_COL;


	@Column(name="CLIENT_ID", nullable = true , length=200)
	private String clientId = null;

	
	@Column(name="FIRST_NAME", nullable = true , length=200)
	private String firstName = null;
	
	@Column(name="LAST_NAME", nullable = true , length=200)
	private String lastName = null;
	
	@Column(name="HOSPITAL_EMPLOYEE")
	private boolean hospitalEmployee = false; //default value for the UI.
	
	

	@Embedded
	@AttributeOverrides({
	        @AttributeOverride(name=Contact.CONTACT_EMAIL, column=@Column(name=EMAIL )),
	        @AttributeOverride(name=Contact.CONTACT_CELL, column=@Column(name=CELL)),
	        @AttributeOverride(name=Contact.CONTACT_HOME_PHONE, column=@Column(name=HOME_PHONE)),
	        @AttributeOverride(name=Contact.CONTACT_OFFICE, column=@Column(name=OFFICE)),
	        @AttributeOverride(name=Contact.CONTACT_BACKLINE, column=@Column(name=BACKLINE)),
	        @AttributeOverride(name=Contact.CONTACT_FAX, column=@Column(name=FAX))
	        
	    })
	private Contact contact = null;
	
	
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name=Address.ADRESS, column=@Column(name=ADRESS)),
        @AttributeOverride(name=Address.ADDRESS_CITY, column=@Column(name=ADDRESS_CITY)),
        @AttributeOverride(name=Address.ADDRESS_ZIP, column=@Column(name=ADDRESS_ZIP)),
        @AttributeOverride(name=Address.ADDRESS_STATE_PROVINCE, column=@Column(name=STATE_PROVINCE)),
        @AttributeOverride(name=Address.ADDRESS_COUNTRY, column=@Column(name=COUNTRY))
    })
	private Address address = null;
	
	
	
	public ClientAttribute() {
		this.contact = new Contact();
		this.address = new Address();
	}
	
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
	public Contact getContactNotNull() {
		if(contact==null)
		{
			return new Contact();
		}
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isHospitalEmployee()
	{
		return hospitalEmployee;
	}

	public void setHospitalEmployee(boolean hospitalEmployee)
	{
		this.hospitalEmployee = hospitalEmployee;
	}
}
