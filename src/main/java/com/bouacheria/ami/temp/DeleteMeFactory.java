package com.bouacheria.ami.temp;

import org.springframework.stereotype.Service;

import com.bouacheria.ami.domain.amiservices.AmiFee;
import com.bouacheria.ami.domain.amiservices.AmiService;
import com.bouacheria.ami.domain.datatype.Breed;
import com.bouacheria.ami.domain.datatype.Labs;
import com.bouacheria.ami.domain.datatype.Species;
@Service
public class DeleteMeFactory {

	
	public Breed getBreed(String name, String species)
	{
		Breed aDomain = new  Breed();
		aDomain.setName(name);
		aDomain.setSpecies(species);
		return aDomain;
	}
	
	public Species getSpecies(String name)
	{
		Species aDomain = new  Species();
		aDomain.setName(name);
		return aDomain;
	}
	
	public Labs getLabs(String name)
	{
		Labs aDomain = new  Labs();
		aDomain.setName(name);
		return aDomain;
	}
	
	public AmiService getAmiService(String name,String category,double price,String description)
	{
		AmiService svc = new  AmiService();
		svc.setCategory(category);
		svc.setName(name);
		svc.setDefaultPrice(price);
		svc.setDescription(description);
		return svc;
	}
	
	public AmiFee getAmiFee(String name,String category, double dollarAmount ,double perAmount, String description, 
			boolean isPercentage)
	{
		AmiFee svc = new  AmiFee();
		svc.setName(name);
		svc.setAmount(0);
		svc.setCategory(category);
		svc.setDollarAmount(dollarAmount);
		svc.setPerAmount(perAmount);
		svc.setDescription(description);
		return svc;
	}
	
	
}
