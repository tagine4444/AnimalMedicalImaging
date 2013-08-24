package com.bouacheria.ami.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bouacheria.ami.domain.hospital.Hospital;

@Component("securityUtil")
public class SecurityUtil {


	public Hospital getHospitalForLoggedinUser()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
		    Object principal = auth.getPrincipal();  
		    if (principal instanceof AmiUserDetail) 
		    {
		        AmiUserDetail user = (AmiUserDetail) principal;
		        return user.getHospital();
		    }
		}
		throw new RuntimeException("Unable to determine which user's hosptial");
	}
	
	
	public String getLoggedHospitalName()
	{
		Hospital hospital = getHospitalForLoggedinUser();
		return hospital.getHospitalAttribute().getHospitalName();
		
	}
	public String getLoggedinUserName()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();  
			if (principal instanceof AmiUserDetail) 
			{
				AmiUserDetail user = (AmiUserDetail) principal;
				return user.getUsername();
			}
		}
		throw new RuntimeException("Unable to determine which user's hosptial");
	}
	
	public String getLoggedinUserEmail()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();  
			if (principal instanceof AmiUserDetail) 
			{
				AmiUserDetail user = (AmiUserDetail) principal;
				return user.getUserEmail();
			}
		}
		throw new RuntimeException("Unable to determine which user's hosptial");
	}
	
	public boolean isUserRole(Permissions permissions)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();  
			if (principal instanceof AmiUserDetail) 
			{
				AmiUserDetail user = (AmiUserDetail) principal;
				Collection<GrantedAuthority> authorities = user.getAuthorities();
				for (Iterator<GrantedAuthority> iterator = authorities.iterator(); iterator.hasNext();) 
				{
					GrantedAuthority grantedAuthority = iterator.next();
					return permissions.name().equals( grantedAuthority.getAuthority());
				}
				return false;
			}
		}
		throw new RuntimeException("Unable to determine which user's hosptial");
	}

}
