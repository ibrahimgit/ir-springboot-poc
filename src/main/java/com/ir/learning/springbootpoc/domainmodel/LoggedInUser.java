package com.ir.learning.springbootpoc.domainmodel;

import java.util.List;

public class LoggedInUser {
	
	private String username;
	private List<String> roles;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
