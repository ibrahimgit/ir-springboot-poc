package com.ir.learning.springbootpoc.domainmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

public class Login {
	
	@NotNull
	@NotEmpty
	@SafeHtml
	private String username;
	@NotNull
	@NotEmpty
	@SafeHtml
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
