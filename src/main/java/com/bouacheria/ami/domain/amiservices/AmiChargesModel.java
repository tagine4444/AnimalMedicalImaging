package com.bouacheria.ami.domain.amiservices;

import java.util.List;


public class AmiChargesModel {
	
	private long caseId;
	private long requestId;
	private AmiService interpretationOnlySvc;
	private AmiService ultrasoundSvc;
	private AmiService radioFluoSvc;
	private AmiService contrastRadioSvc;
	private AmiService computedTomoSvc;
	private AmiService MRISvc;
	//private AmiFee amiFee;
//	private AmiFee latePaymentFee;
//	private AmiFee milageFee;
	
	private String latePaymentFee;
	private String milageFee;
	
	private List<AmiCharge> amiCharges;
	private AmiChargeTotal amiChargesTotal;
	
	private List<AmiService> MRISvcList ;
	private List<AmiService> interpretationOnlySvcList ;
	private List<AmiService> ultrasoundSvcList ;
	private List<AmiService> radioFluoSvcList ;
	private List<AmiService> contrastRadioSvcList ;
	private List<AmiService> computedTomoSvcList ;
	private String miscDescription;
	private double miscPrice;
	
	
	
//	public AmiFee getAmiFee() {
//		return amiFee;
//	}
//	public void setAmiFee(AmiFee amiFee) {
//		this.amiFee = amiFee;
//	}
	
	public List<AmiCharge> getAmiCharges() {
		return amiCharges;
	}
	public void setAmiCharges(List<AmiCharge> amiCharges) {
		this.amiCharges = amiCharges;
	}
	public AmiChargeTotal getAmiChargesTotal() {
		return amiChargesTotal;
	}
	public void setAmiChargesTotal(AmiChargeTotal amiChargesTotal) {
		this.amiChargesTotal = amiChargesTotal;
	}
	public AmiService getInterpretationOnlySvc() {
		return interpretationOnlySvc;
	}
	public void setInterpretationOnlySvc(AmiService interpretationOnlySvc) {
		this.interpretationOnlySvc = interpretationOnlySvc;
	}
	public AmiService getUltrasoundSvc() {
		return ultrasoundSvc;
	}
	public void setUltrasoundSvc(AmiService ultrasoundSvc) {
		this.ultrasoundSvc = ultrasoundSvc;
	}
	public AmiService getRadioFluoSvc() {
		return radioFluoSvc;
	}
	public void setRadioFluoSvc(AmiService radioFluoSvc) {
		this.radioFluoSvc = radioFluoSvc;
	}
	public AmiService getContrastRadioSvc() {
		return contrastRadioSvc;
	}
	public void setContrastRadioSvc(AmiService contrastRadioSvc) {
		this.contrastRadioSvc = contrastRadioSvc;
	}
	public AmiService getComputedTomoSvc() {
		return computedTomoSvc;
	}
	public void setComputedTomoSvc(AmiService computedTomoSvc) {
		this.computedTomoSvc = computedTomoSvc;
	}
	public AmiService getMRISvc() {
		return MRISvc;
	}
	public void setMRISvc(AmiService mRISvc) {
		MRISvc = mRISvc;
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
	public List<AmiService> getMRISvcList() {
		return MRISvcList;
	}
	public void setMRISvcList(List<AmiService> mRISvcList) {
		MRISvcList = mRISvcList;
	}
	public List<AmiService> getInterpretationOnlySvcList() {
		return interpretationOnlySvcList;
	}
	public void setInterpretationOnlySvcList(
			List<AmiService> interpretationOnlySvcList) {
		this.interpretationOnlySvcList = interpretationOnlySvcList;
	}
	public List<AmiService> getUltrasoundSvcList() {
		return ultrasoundSvcList;
	}
	public void setUltrasoundSvcList(List<AmiService> ultrasoundSvcList) {
		this.ultrasoundSvcList = ultrasoundSvcList;
	}
	public List<AmiService> getRadioFluoSvcList() {
		return radioFluoSvcList;
	}
	public void setRadioFluoSvcList(List<AmiService> radioFluoSvcList) {
		this.radioFluoSvcList = radioFluoSvcList;
	}
	public List<AmiService> getContrastRadioSvcList() {
		return contrastRadioSvcList;
	}
	public void setContrastRadioSvcList(List<AmiService> contrastRadioSvcList) {
		this.contrastRadioSvcList = contrastRadioSvcList;
	}
	public List<AmiService> getComputedTomoSvcList() {
		return computedTomoSvcList;
	}
	public void setComputedTomoSvcList(List<AmiService> computedTomoSvcList) {
		this.computedTomoSvcList = computedTomoSvcList;
	}
//	public AmiFee getLatePaymentFee() {
//		return latePaymentFee;
//	}
//	public void setLatePaymentFee(AmiFee latePaymentFee) {
//		this.latePaymentFee = latePaymentFee;
//	}
//	public AmiFee getMilageFee() {
//		return milageFee;
//	}
//	public void setMilageFee(AmiFee milageFee) {
//		this.milageFee = milageFee;
//	}
	public String getLatePaymentFee() {
		return latePaymentFee;
	}
	public void setLatePaymentFee(String latePaymentFee) {
		this.latePaymentFee = latePaymentFee;
	}
	public String getMilageFee() {
		return milageFee;
	}
	public void setMilageFee(String milageFee) {
		this.milageFee = milageFee;
	}
	public String getMiscDescription() {
		return miscDescription;
	}
	public void setMiscDescription(String miscDescription) {
		this.miscDescription = miscDescription;
	}
	public double getMiscPrice() {
		return miscPrice;
	}
	public void setMiscPrice(double miscPrice) {
		this.miscPrice = miscPrice;
	}
	
}
