package com.bouacheria.ami.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.bouacheria.ami.domain.hospital.Hospital;

public class AmiUserDetail extends User{

	private static final long serialVersionUID = 1L;

	private Hospital hospital;
	private String userEmail;
	
	public AmiUserDetail(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Hospital hospital, String userEmail) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		
		this.hospital = hospital;
		this.userEmail = userEmail;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public String getUserEmail() {
		return userEmail;
	}

}
