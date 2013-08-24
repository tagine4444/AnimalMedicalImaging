package com.bouacheria.ami.domain.amiservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bouacheria.ami.service.datatype.AmiServiceCategory;

@Entity
@Table(schema = "amischema", name = "AMI_CHARGE")
public class AmiCharge {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	
	@Column(name="service_request_id")
	private Long serviceRequestId = null;
	
	@Column(name="ami_case_id")
	private Long amiCaseId = null;
	
	@Column(name="service_id")
	private Long serviceId = null;
	
	@Column(name="service_name",length=255)
	private String serviceName = null;
	
	@Column(name="category",length=255)
	private String category= null;

	@Column(name="service_price")
	private double servicePrice= 0;
	
	@Column(name="service_description",length=1000)
	private String serviceDescription= "";
	
	@Column(name="price")
	private double price= 0;
	
	@Column(name="description",length=1000)
	private String description = null;

	
	public void basedOnAmiService(AmiService amiService) 
	{
		this.serviceId 	  = amiService.getId();
		this.serviceName  = amiService.getName();
		this.category 	  = amiService.getCategory();
		this.servicePrice = amiService.getDefaultPrice();
		this.serviceDescription = amiService.getDescription();
		this.price 		 = amiService.getDefaultPrice();
		
	}
	
	public void basedOnAmiService(AmiMilageFeeModel milageFeeModel) 
	{
		this.serviceId 	  = milageFeeModel.getAmiFee().getId();
		this.serviceName  = milageFeeModel.getAmiFee().getName();
		this.category 	  = milageFeeModel.getAmiFee().getCategory();
		this.serviceDescription = milageFeeModel.getDefaultDescription() ;
		this.price = milageFeeModel.getAmount();
		this.amiCaseId = milageFeeModel.getCaseId();
		this.serviceRequestId = milageFeeModel.getRequestId();
		this.servicePrice = milageFeeModel.getAmiFee().getDollarAmount();
	}
	
	public Long getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(Long serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public Long getAmiCaseId() {
		return amiCaseId;
	}

	public void setAmiCaseId(Long amiCaseId) {
		this.amiCaseId = amiCaseId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	
}
