package com.bouacheria.ami.domain.hospital;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import com.bouacheria.ami.domain.enums.HospitatlPriorityType;
import com.bouacheria.ami.domain.users.AMIUser;


@Entity
@Table(schema = "amischema", name = "HOSPITAL")
public class Hospital {

	
	@Id
	@GeneratedValue
	protected Long id = null;

	@Transient
	public final static int PRIORITY_NORMAL =0;
	
	@Transient
	public final static int PRIORITY_HIGH =1;
	
	@Transient
	private AMIUser amiUser;

	@Transient
	private String hospitalEmailConfirm;
	
	@Column(name="priority")
	private int priority =0;
	
	@Column(name="UNDER_CONTRACT", nullable=false)
	private boolean underContract=false;
	
	@Column(name="BILLING_CYCLE")
	private int billingCycle = 0;
	
	
	
//	@Transient
//	private EncryptorService encryptor;

	@Embedded
	private HospitalAttribute hospitalAttribute = null;
	
	
	public Hospital() {
		this.hospitalAttribute = new HospitalAttribute();
		this.amiUser = new AMIUser();
		//this.encryptor = new SHAEncryptorServiceImpl();
	}
	
	
	public Long getId() {
		return id;
	}

	public HospitalAttribute getHospitalAttribute() {
		return hospitalAttribute;
	}

	public void setHospitalAttribute(HospitalAttribute hospitalAttribute) {
		this.hospitalAttribute = hospitalAttribute;
	}
	

	
	@Transient
	public String getName()
	{
		return hospitalAttribute.getHospitalName();
	}
	@Transient
	public String getLab()
	{
		return hospitalAttribute.getLab();
	}
	@Transient
	public String getLabAccount()
	{
		return hospitalAttribute.getLabAccount();
	}
	
	@Transient
	public String getAddress()
	{
		return hospitalAttribute.getAddress().getAddress();
	}
	
	@Transient
	public String getCity()
	{
		return hospitalAttribute.getAddress().getCity();
	}
	@Transient
	public String getStateProvince()
	{
		return hospitalAttribute.getAddress().getStateProvince();
	}
	@Transient
	public int getZip()
	{
		return hospitalAttribute.getAddress().getZip();
	}
	@Transient
	public String getCountry()
	{
		return hospitalAttribute.getAddress().getCountry();
	}
	
	@Transient
	public String getCell()
	{
		return hospitalAttribute.getContact().getCell();
	}
	
	@Transient
	public String getHospitalEmail()
	{
		return hospitalAttribute.getContact().getEmail();
	}
	

	@Transient
	public String getHospitalEmailConfirm() {
		return hospitalEmailConfirm;
	}


	@Transient
	public void setHospitalEmailConfirm(String hospitalEmailConfirm) {
		this.hospitalEmailConfirm = hospitalEmailConfirm;
	}
	
	@Transient
	public String getFax()
	{
		return hospitalAttribute.getContact().getFax();
	}	
	
	@Transient
	public String getOffice()
	{
		return hospitalAttribute.getContact().getOffice();
	}	
	
	@Transient
	public String getBackLine()
	{
		return hospitalAttribute.getContact().getBackLine();
	}	
	
	@Transient
	public void setName(String name)
	{
		hospitalAttribute.setHospitalName(name);
	}
	@Transient
	public void setLab(String lab)
	{
		hospitalAttribute.setLab(lab);
	}
	@Transient
	public void setLabAccount(String account)
	{
		hospitalAttribute.setLabAccount(account);
	}
	
	@Transient
	public void setAddress(String address)
	{
		hospitalAttribute.getAddress().setAddress(address);
	}
	
	@Transient
	public void setCity(String city)
	{
		hospitalAttribute.getAddress().setCity(city);
	}
	@Transient
	public void setStateProvince(String stateProvince)
	{
		hospitalAttribute.getAddress().setStateProvince(stateProvince);
	}
	@Transient
	public void setZip(int zip)
	{
		hospitalAttribute.getAddress().setZip(zip);
	}
	@Transient
	public void setCountry(String country)
	{
		hospitalAttribute.getAddress().setCountry(country);
	}
	
	@Transient
	public void setCell(String cell)
	{
		hospitalAttribute.getContact().setCell(cell);
	}
	
	@Transient
	public void setHospitalEmail(String email1)
	{
		hospitalAttribute.getContact().setEmail(email1);
	}
	
	@Transient
	public void setFax(String fax)
	{
		hospitalAttribute.getContact().setFax(fax);
	}	
	
	@Transient
	public void setOffice(String office)
	{
		hospitalAttribute.getContact().setOffice(office);
	}	
	@Transient
	public void setBackLine(String backLine)
	{
		hospitalAttribute.getContact().setBackLine(backLine);
	}
	

	public String getAcronym() {
		return this.hospitalAttribute.getAcronym();
	}


	public void setAcronym(String acronym) {
		this.hospitalAttribute.setAcronym(acronym);
	}


	public int getPriority() {
		return priority;
	}
	
	public String getPriorityString() {
		return HospitatlPriorityType.getValue(priority) ;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public boolean isUnderContract() {
		return underContract;
	}


	public void setUnderContract(boolean underContract) {
		this.underContract = underContract;
	}


	public AMIUser getAmiUser() {
		return amiUser;
	}


	public void setAmiUser(AMIUser amiUser) {
		this.amiUser = amiUser;
	}
	
	
	@Transient
	public String getUserName() {
		return amiUser.getUserName();
	}


	@Transient
	public void setUserName(String userName) {
		this.amiUser.setUserName(userName);
	}


	@Transient
	public String getUserPwd() {
		return amiUser.getPwd();
	}


	@Transient
	public void setUserPwd(String password) {
		this.amiUser.setPwd(password);
	}	
	

	@Transient
	public String getUserEmail() {
		return amiUser.getEmail();
	}


	@Transient
	public void setUserEmail(String email) {
		this.amiUser.setEmail(email);
	}


	@Transient
	public String getFirstName() {
		return amiUser.getFirstName();
	}


	@Transient
	public void setFirstName(String firstName) {
		amiUser.setFirstName(firstName);
	}


	@Transient
	public String getLastName() {
		return amiUser.getLastName();
	}


	@Transient
	public void setLastName(String lastName) {
		this.amiUser.setLastName(lastName);
	}


	@Transient
	public String getConfirmUserPassword() {
		return amiUser.getConfirmPassword();
	}


	@Transient
	public void setConfirmUserPassword(String confirmPassword) {
		this.amiUser.setConfirmPassword(confirmPassword);
	}


	@Transient
	public String getUserConfirmEmail() {
		return amiUser.getConfirmEmail();
	}


	@Transient
	public void setUserConfirmEmail(String confirmEmail) {
		this.amiUser.setConfirmEmail(confirmEmail);
	}

	@Transient
	public String getOccupation() {
		return amiUser.getOccupation();
	}
	
	@Transient
	public void setOccupation(String occupation) {
		amiUser.setOccupation(occupation);
	}
	
	public void clone(Hospital hospital)
	{
		hospital.setHospitalAttribute(getHospitalAttribute());
		hospital.setAmiUser(this.amiUser);
		hospital.setPriority(this.priority);
		hospital.setUnderContract(underContract);
		hospital.setBillingCycle(this.billingCycle);
	}


	public int getBillingCycle() {
		return billingCycle;
	}


	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}


}
