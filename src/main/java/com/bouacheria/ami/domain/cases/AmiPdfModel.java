package com.bouacheria.ami.domain.cases;


public class AmiPdfModel
{
	private String reportTitle;   // RADIOGRAPHIC INTERPRETATION REPORT
	private String companyTitle;  // Animal Medical Imaging
	private String address;       // First Place West Bothell, WA  98021
	private String phone;         // 425-419-4220 or 800-888-0197 Fax: 425-949-8143
	
	public String getReportTitle()
	{
		return reportTitle;
	}
	public void setReportTitle(String reportTitle)
	{
		this.reportTitle = reportTitle;
	}
	public String getCompanyTitle()
	{
		return companyTitle;
	}
	public void setCompanyTitle(String companyTitle)
	{
		this.companyTitle = companyTitle;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	
}
