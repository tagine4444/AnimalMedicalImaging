package com.bouacheria.ami.domain.datatype;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	
	// these field MUST match the below variables
	public final static String CONTACT_EMAIL   = "email";
	public final static String CONTACT_CELL     = "cell";
	public final static String CONTACT_HOME_PHONE     = "homePhone";
	public final static String CONTACT_OFFICE   = "office";
	public final static String CONTACT_BACKLINE = "backLine";
	public final static String CONTACT_FAX      = "fax";
	
	public final static String EMAIL_COL   = "EMAIL";
	public final static String OFFICE_COL   = "OFFICE_PHONE";
	public final static String CELL_COL     = "CELL_PHONE";
	public final static String HOMEPHONE_COL     = "HOME_PHONE";
	//public final static String HOME_COL     = "HOME_COL";
	public final static String BACKLINE_COL = "BACKLINE_PHONE";
	public final static String FAX_COL      = "FAX";
	

	@Column(name=EMAIL_COL ,length=100)
	private String email = null;
	
	
	@Column(name=CONTACT_CELL)
	private String cell;
	
	
	@Column(name=CONTACT_HOME_PHONE)
	private String homePhone;
	
	
	@Column(name=OFFICE_COL)
	private String office;
	
	@Column(name=BACKLINE_COL)
	private String backLine ;
	
	@Column(name=FAX_COL)
	private String fax;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getBackLine() {
		return backLine;
	}

	public void setBackLine(String backLine) {
		this.backLine = backLine;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getHomePhone()
	{
		return homePhone;
	}

	public void setHomePhone(String homePhone)
	{
		this.homePhone = homePhone;
	}
}
