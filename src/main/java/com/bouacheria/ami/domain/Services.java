package com.bouacheria.ami.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Services {

	@Column(name="MRI_SVC", nullable = true , length=200)
	private String mriSvc  = "";
	
	@Column(name="RADIO_FLUO_SVC", nullable = true , length=200)
	private String radiographyFluoroscopy  = "";
	
	@Column(name="CONTRAST_RADIO_SVC", nullable = true , length=200)
	private String contrastRadiographySvc  = "";
	
	@Column(name="COMPUTED_TOMOGRAPHY_SVC", nullable = true , length=200)
	private String computedTomographySvc  = "";
	
	@Column(name="ULTRASOUND_SVC", nullable = true , length=200)
	private String ultrasoundSvc  = "";

	public String getMriSvc() {
		return mriSvc;
	}

	public void setMriSvc(String mriSvc) {
		this.mriSvc = mriSvc;
	}

	public String getRadiographyFluoroscopy() {
		return radiographyFluoroscopy;
	}

	public void setRadiographyFluoroscopy(String radiographyFluoroscopy) {
		this.radiographyFluoroscopy = radiographyFluoroscopy;
	}

	public String getContrastRadiographySvc() {
		return contrastRadiographySvc;
	}

	public void setContrastRadiographySvc(String contrastRadiographySvc) {
		this.contrastRadiographySvc = contrastRadiographySvc;
	}

	public String getComputedTomographySvc() {
		return computedTomographySvc;
	}

	public void setComputedTomographySvc(String computedTomographySvc) {
		this.computedTomographySvc = computedTomographySvc;
	}

	public String getUltrasoundSvc() {
		return ultrasoundSvc;
	}

	public void setUltrasoundSvc(String ultrasoundSvc) {
		this.ultrasoundSvc = ultrasoundSvc;
	}
	
	
	
}
