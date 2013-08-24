package com.bouacheria.ami.domain.enums;

import com.bouacheria.ami.domain.hospital.Hospital;

public enum HospitatlPriorityType {

	NORMAL(Hospital.PRIORITY_NORMAL ,"Normal"),
	HIGH(Hospital.PRIORITY_HIGH,"High");
	
	private final int priority;
	private final String value;
	
	HospitatlPriorityType(int priority, String value)
	{
		this.priority = priority;
		this.value = value;
	}

	public int getPriority() {
		return priority;
	}

	public String getValue() {
		return value;
	}
	
	public static String getValue(int priority)
	{
		if(priority ==Hospital.PRIORITY_NORMAL)
		{
			return HospitatlPriorityType.NORMAL.getValue();
		}
		if(priority == Hospital.PRIORITY_HIGH)
		{
			return HospitatlPriorityType.HIGH.getValue();
		}
		throw new IllegalArgumentException("Invalid priority");
	}
}
