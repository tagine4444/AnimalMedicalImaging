package com.bouacheria.ami.domain.amiservices;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bouacheria.ami.service.datatype.AmiServiceCategory;

@Entity
@Table(schema = "amischema", name = "AMI_FEE")
public class AmiFee {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	
	@Column(name="name",length=255)
	private String name = null;
	
	@Column(name="category",length=255)
	private String category = null;
	
	@Column(name="per_amount")
	private double perAmount;
	
	@Column(name="dollar_amount")
	private double dollarAmount;
	
	@Column(name="IS_PERCENTAGE")
	private boolean isPercentage;

	@Column(name="description",length=1000)
	private String description = null;

	@Column(name="amount")
	private double amount = 0;
	
	
	public String getName() {
		return name;
	}
	
	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPerAmount() {
		return perAmount;
	}

	public void setPerAmount(double perAmount) {
		this.perAmount = perAmount;
	}

	public double getDollarAmount() {
		return dollarAmount;
	}

	public void setDollarAmount(double dollarAmount) {
		this.dollarAmount = dollarAmount;
	}

	public boolean isPercentage() {
		return isPercentage;
	}

	public void setPercentage(boolean isPercentage) {
		this.isPercentage = isPercentage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public AmiFee clone()
	{
		AmiFee fee = new AmiFee();
		fee.setId(id);
		fee.setAmount(amount);
		fee.setCategory(category);
		fee.setDescription(description);
		fee.setDollarAmount(dollarAmount);
		fee.setName(name);
		fee.setPerAmount(perAmount);
		fee.setPercentage(isPercentage);
		return fee;
		
	}

	private void setId(Long id) {
		this.id = id;
	}
}
