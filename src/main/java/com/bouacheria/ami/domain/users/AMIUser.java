package com.bouacheria.ami.domain.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.bouacheria.ami.security.Permissions;

@Entity
@Table(schema = "amischema", name = "AMIUSER")
public class AMIUser {

//	@Id
//	@GeneratedValue
//	private Long id;
	
	@Id
	@NotEmpty
	@Column(name="USER_NAME")
	private String userName;
	
	@NotEmpty
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@NotEmpty
	@Column(name="LAST_NAME")
	private String lastName;
	
	
	@Column(name="HOSPITAL_ID", nullable = false)
	private Long hospitalId = null;
	
	@NotEmpty
	@Column(name="PWD", nullable = false)
	private String pwd;
	
	@NotEmpty
	@Column(name="OCCUPATION", nullable = false)
	private String occupation;
	
	@Transient
	private String confirmPassword;
	
	@NotEmpty
	@Column(name="EMAIL", nullable = false)
	private String email;
	
	@Column(name="DELETE_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime deleteTime;
	
	@Column(name="DELETE_STRING")
	private String deleteBy;
	
	@Transient
	private String confirmEmail;
	
	
	@Column(name="ACCOUNT_ACTIVE", nullable = false)
	private boolean accountActive= false;
	
	@Column(name="PERMISSION", nullable = false)
	private String permission;
	
	
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String password) {
		this.pwd = password;
	}	
	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isAccountActive() {
		return accountActive;
	}
	
	public String getAccountActiveString() {
		if(accountActive)
		{
			return "Yes";
		}
		return "No";
	}


	public void setAccountActive(boolean accountActive) {
		this.accountActive = accountActive;
	}


	public String getPermission() {
		return permission;
	}


	public void setPermission(String permission) {
		this.permission = permission;
	}


	public Long getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getConfirmEmail() {
		return confirmEmail;
	}


	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}


	public DateTime getDeleteTime() {
		return deleteTime;
	}


	public void setDeleteTime(DateTime deleteTime) {
		this.deleteTime = deleteTime;
	}


	public String getDeleteBy() {
		return deleteBy;
	}


	public void setDeleteBy(String deleteBy) {
		this.deleteBy = deleteBy;
	}


	public String getOccupation() {
		return occupation;
	}


	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public boolean getAdminUser()
	{
		if(Permissions.ROLE_ADMIN.name().equals(permission) ||
		   Permissions.ROLE_TRANSCRIBER.name().equals(permission))
		{
			return true;
		}
		return false;
	}
	
	
	public List<String> getErrorOnUserCreation(AMIUser currentDBuser)
	{
		List<String> errors = new ArrayList<String>();
		boolean isPwdChanged = !currentDBuser.getPwd().equals(getPwd());
		boolean isEmailChanged = !currentDBuser.getEmail().equals(getEmail());
		
		boolean isConfirmedPwdEntered   = StringUtils.isNotEmpty(getConfirmPassword());
		boolean isConfirmedEmailEntered = StringUtils.isNotEmpty(getConfirmEmail());
		
		if(isPwdChanged)
		{
			if(!getPwd().equals(getConfirmPassword()))
			{
				errors.add("The password has been changed, and doesn't match the confimed password.");
			}
		}
		if(isConfirmedPwdEntered)
		{
			if(!getPwd().equals(getConfirmPassword()))
			{
				errors.add("The password and doesn't match the confimed password.");
			}
		}
		
		if(isEmailChanged)
		{
			if(!getEmail().equals(getConfirmEmail()))
			{
				errors.add("The Email has been changed, and doesn't match the confimed Email.");
			}
		}
		if(isConfirmedEmailEntered)
		{

			if(!getEmail().equals(getConfirmEmail()))
			{
				errors.add("The Email doesn't match the confimed Email.");
			}
		}
		
		return errors;
	}

}
