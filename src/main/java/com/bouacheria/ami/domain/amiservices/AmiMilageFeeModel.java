package com.bouacheria.ami.domain.amiservices;

import com.bouacheria.ami.service.datatype.AmiServiceCategory;

public class AmiMilageFeeModel {

	private long caseId;
	private long requestId;
	private AmiFee amiFee;
	private double amount;
	private double perAmount;
	private double dollarAmount;

	public AmiFee getAmiFee() {
		return amiFee;
	}

	public void setAmiFee(AmiFee milageFee) {
		this.amiFee = milageFee;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public String getDefaultDescription()
	{
		final String category = getAmiFee().getCategory();
		
		if(AmiServiceCategory.isLateFee(category))
		{
			return    dollarAmount + " % of " + perAmount;
		}
		if(AmiServiceCategory.isMilageFee(category))
		{
			return perAmount + " Miles at $" + dollarAmount + " per mile";
		}
		
		return amiFee.getDescription();
	}

}
