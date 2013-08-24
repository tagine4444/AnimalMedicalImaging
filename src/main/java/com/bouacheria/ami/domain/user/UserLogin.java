package com.bouacheria.ami.domain.user;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLogin {

	@NotEmpty
	private String user;
	@NotEmpty
	private String pwd;
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
