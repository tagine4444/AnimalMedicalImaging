package com.bouacheria.ami.domain.exam;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamAttribute {

	@Column(name="ANESTHESIZE")
	private boolean anesthesized= false;
	
	@Column(name="SEDATED")
	private boolean sedated= false;
	
	@Column(name="FASTED")
	private boolean fasted= false;
	
	@Column(name="ENEMA")
	private boolean enema= false;
	
	@Column(name="PAINFUL")
	private boolean painful= false;
	
	@Column(name="FRACTIOUS")
	private boolean fractious= false;
	
	@Column(name="SHOCKY")
	private boolean shocky= false;
	
	@Column(name="DYSPNEIC")
	private boolean dyspneic = false;
	
	@Column(name="DIED")
	private boolean died = false;
	
	@Column(name="EUTHANIZED")
	private boolean euthanized = false;
	
	

	public boolean isAnesthesized() {
		return anesthesized;
	}

	public void setAnesthesized(boolean anesthesized) {
		this.anesthesized = anesthesized;
	}

	public boolean isSedated() {
		return sedated;
	}

	public void setSedated(boolean sedated) {
		this.sedated = sedated;
	}

	public boolean isFasted() {
		return fasted;
	}

	public void setFasted(boolean fasted) {
		this.fasted = fasted;
	}

	public boolean isEnema() {
		return enema;
	}

	public void setEnema(boolean enema) {
		this.enema = enema;
	}

	public boolean isPainful() {
		return painful;
	}

	public void setPainful(boolean painful) {
		this.painful = painful;
	}

	public boolean isFractious() {
		return fractious;
	}

	public void setFractious(boolean fractious) {
		this.fractious = fractious;
	}

	public boolean isShocky() {
		return shocky;
	}

	public void setShocky(boolean shocky) {
		this.shocky = shocky;
	}

	public boolean isDyspneic() {
		return dyspneic;
	}

	public void setDyspneic(boolean dyspneic) {
		this.dyspneic = dyspneic;
	}

	public boolean isDied() {
		return died;
	}

	public void setDied(boolean died) {
		this.died = died;
	}

	public boolean isEuthanized() {
		return euthanized;
	}

	public void setEuthanized(boolean euthanized) {
		this.euthanized = euthanized;
	}
}
