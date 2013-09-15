package com.bouacheria.ami.domain.cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmiPdfHolder
{

	@Autowired
	private AmiPdfFactory factory;
	
	
	private AmiPdfModel amiPdfModel= null;
	

	public AmiPdfModel getAmiPdfModel()
	{
		if(amiPdfModel==null)
		{
			this.amiPdfModel = factory.getAmiPdfModel();
		}
		return this.amiPdfModel;
	}
	
}
