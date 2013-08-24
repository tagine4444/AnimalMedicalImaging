package com.bouacheria.ami.domain.amiservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(schema = "amischema", name = "AMI_SERVICES")
public class AmiService implements Comparable<String>
{
	@Id
	@GeneratedValue
	protected Long id = null;
	
	@Column(name="name",length=255)
	private String name = null;
	
	@Column(name="category",length=255)
	private String category = null;
	
	@Column(name="default_price")
	private double defaultPrice= 0;

	@Column(name="price_min")
	private double priceMin= 0;
	
	@Column(name="price_max")
	private double priceMax= 0;
	
	@Column(name="description",length=1000)
	private String description = null;
	
	@Column(name="DEACTIVATED_DATE")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime deactivatedDate = null;
	
	@Column(name="DEACTIVATED_BY")
	private String deactivatedBy = null;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public AmiService basedOnAmiService(AmiServiceModel amiServiceModel) 
	{
		AmiService svc = new AmiService();
		svc.setCategory(amiServiceModel.getCategory());
		svc.setDefaultPrice(amiServiceModel.getDefaultPrice());
		svc.setDescription(amiServiceModel.getDescription());
		svc.setName(amiServiceModel.getName());
		svc.setPriceMax(amiServiceModel.getPriceMax());
		svc.setPriceMin(amiServiceModel.getPriceMin());
		
		return svc;
	}
	
	public void updateBasedOnAmiService(AmiServiceModel amiServiceModel) 
	{
		this.name = amiServiceModel.getName();
		this.category = amiServiceModel.getCategory();
		this.defaultPrice = amiServiceModel.getDefaultPrice();
		this.description = amiServiceModel.getDescription();
		this.priceMin = amiServiceModel.getPriceMin();
		this.priceMax = amiServiceModel.getPriceMax();
	}
	
	public AmiService clone()
	{ 
		AmiService svc = new AmiService();
		svc.setId(id);
		svc.setCategory(category);
		svc.setDefaultPrice(defaultPrice);
		svc.setDescription(description);
		svc.setName(name);
		svc.setPriceMax(priceMax);
		svc.setPriceMin(priceMin);
		
		return svc;
	}

	@Override
	public int compareTo(String o) {
		return o.compareTo(this.name);
	}

	public DateTime getDeactivatedDate() {
		return deactivatedDate;
	}

	public void setDeactivatedDate(DateTime deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}

	public String getDeactivatedBy() {
		return deactivatedBy;
	}

	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}

}
