package com.bouacheria.ami.domain.datatype;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.NumberFormat;

@Embeddable
public class Address {
	
	// these field MUST match the below variables
	public final static String ADRESS   				  = "address";
	public final static String ADDRESS_CITY   		  = "city";
	public final static String ADDRESS_ZIP     		  = "zip";
	public final static String ADDRESS_STATE_PROVINCE  = "stateProvince";
	public final static String ADDRESS_COUNTRY  = "country";
	
	
	public final static String ADRESS_COL   		  = "ADDRESS";
	public final static String CITY_COL   		  = "CITY";
	public final static String ZIP_COL     		  = "ZIP";
	public final static String STATE_PROVINCE_COL  = "STATE";
	public final static String COUNTRY_COL  = "COUNTRY";
	

	@Column(name=ADRESS_COL, nullable = true , length=500)
	private String address = null;

	@Column(name=CITY_COL, nullable = true, length=150)
	private String city = null;
	
	@Column(name=ZIP_COL, nullable = true)
	@NumberFormat
	private int zip = 0;
	
	@Column(name=STATE_PROVINCE_COL, nullable = true, length=500)
	private String stateProvince = null;

	@Column(name=COUNTRY_COL, nullable = true, length=150)
	private String country = null;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
