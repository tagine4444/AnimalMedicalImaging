	package com.bouacheria.ami.service.datatype;


public enum AmiServiceCategory {
	INTERPRETATION_ONLY("INTERPRETATION_ONLY", "Interpretation Only"),
	MRI("MRI", "MRI"),
	RADIOGRAPHYFLUOROSCOPY("RADIOGRAPHYFLUOROSCOPY", "Radiography/Fluoroscopy"),
	ULTRASOUND("ULTRASOUND", "Ultrasound"),
	COMPUTEDTOMOGRAPHY("COMPUTEDTOMOGRAPHY","Computed Tomography"),
	CONTRASTEDRADIOGRAPHY("CONTRASTEDRADIOGRAPHY", "Contrasted Radiography"),
	MILAGE_FEE("MILAGE_FEE","Milage Fee"),
	LATE_PAYMENT_FEE("LATE_PAYMENT_FEE", "Late Payment Fee"),
	MISC_SERVICE("MISC_SERVICE", "Miscellenous Service");
	
	private final String code;
	private final String name;
	
	
	AmiServiceCategory(String code, String name)
	{
		this.code = code;
		this.name= name;
	}


	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
//	public static String getInterpretationOnlyLabel()
//	{
//		return "Interpretation Only";
//	}


	
	public static boolean isLateFee(String category)
	{
		return LATE_PAYMENT_FEE.getCode().equals(category);
	}

	public static boolean isMilageFee(String category)
	{
		return MILAGE_FEE.getCode().equals(category);
	}


	
	
}
