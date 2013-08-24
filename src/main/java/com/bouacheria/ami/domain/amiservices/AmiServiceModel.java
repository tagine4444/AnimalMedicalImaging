package com.bouacheria.ami.domain.amiservices;

import org.hibernate.validator.constraints.NotEmpty;


public class AmiServiceModel {
	
	private long id = 0;
	
	@NotEmpty
	private String name = null;
	@NotEmpty
	private String category = null;
	
	private double defaultPrice= 0;
	private double priceMin= 0;
	private double priceMax= 0;
	@NotEmpty
	private String description = null;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public double getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(double priceMin) {
		this.priceMin = priceMin;
	}

	public double getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(double priceMax) {
		this.priceMax = priceMax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void reset()
	{
		this.name = "";
		this.category ="";
		this.defaultPrice = 0;
		this.description = "";
		this.priceMin= 0;
		this.priceMax= 0;
	}
	
	public void basedOnAmiService(AmiService amiService)
	{
		this.id = amiService.getId();
		this.name = amiService.getName();
		this.category = amiService.getCategory();
		this.defaultPrice = amiService.getDefaultPrice();
		this.description = amiService.getDescription();
		this.priceMin = amiService.getPriceMin();
		this.priceMax = amiService.getPriceMax();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
