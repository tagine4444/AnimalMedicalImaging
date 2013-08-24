package com.bouacheria.ami.factory;

import org.springframework.stereotype.Component;

import com.bouacheria.ami.domain.request.RequestSequence;
import com.bouacheria.ami.domain.users.AMIUser;
import com.bouacheria.ami.security.Permissions;

@Component
public class UserFactory {

	
	public RequestSequence getRequestSequence(String hospitalName, long hospitalId)
	{
		RequestSequence r = new RequestSequence();
		r.setHospitalId(hospitalId);
		r.setHospitalName(hospitalName);
		r.setNextNumber(1L);
		return r;
		
	}	

	/**
	 * @param userName
	 * @param pwd
	 * @param hospitalId
	 * @param email
	 * @return
	 * 
	 * should not be used only in the DeleteService
	 */
	public AMIUser getActiveVet(String userName, String pwd, long hospitalId,	String email, String fName, String lName, String occupation)
	{
		AMIUser user = new AMIUser();
		user.setFirstName(fName);
		user.setLastName(lName);
		user.setAccountActive(true);
		user.setPermission(Permissions.ROLE_VET.name());
		user.setUserName(userName);
		user.setPwd(pwd);
		user.setHospitalId(hospitalId);
		user.setEmail(email);
		user.setOccupation(occupation);
		
		
		return user;
	}
	
	public void initActiveVet(AMIUser user ,long hospitalId)
	{
		user.setAccountActive(true);
		user.setPermission(Permissions.ROLE_VET.name());
		user.setHospitalId(hospitalId);
		
	}
	public AMIUser getActiveAdmin(String userName, String pwd, long hospitalId)
	{
		AMIUser user = new AMIUser();
		user.setHospitalId(hospitalId);
		user.setFirstName("Charles");
		user.setLastName("Root");
		user.setAccountActive(true);
		user.setEmail("tagine4444@yahoo.com");
		user.setHospitalId(1L);
		user.setPermission(Permissions.ROLE_ADMIN.name());
		user.setUserName(userName);
		user.setOccupation("Radiologist");
		user.setPwd(pwd);
		
		return user;
	}
	public AMIUser getActiveTranscriber(String userName, String pwd, long hospitalId)
	{
		AMIUser user = new AMIUser();
		user.setHospitalId(hospitalId);
		user.setFirstName("Joann");
		user.setLastName("Root");
		user.setAccountActive(true);
		user.setEmail("tagine4444@yahoo.com");
		user.setHospitalId(1L);
		user.setPermission(Permissions.ROLE_TRANSCRIBER.name());
		user.setUserName(userName);
		user.setPwd(pwd);
		user.setOccupation("Transcriber");		
		return user;
	}
}
