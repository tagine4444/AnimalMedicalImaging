package com.bouacheria.ami.domain.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AmiPdfFactory
{
	@Autowired
	private Environment env;
	
	
	public AmiPdfModel getAmiPdfModel()
	{
		String reportTitle = env.getProperty("pdf.report.title");
		String companyTitle = env.getProperty("pdf.company.title");
		String address = env.getProperty("pdf.address");
		String phone = env.getProperty("pdf.phone");

		
		AmiPdfModel model = new AmiPdfModel();

		model.setReportTitle(reportTitle);
		model.setCompanyTitle(companyTitle);
		model.setPhone(phone);
		model.setAddress(address);
		
		return model;
	}
}
