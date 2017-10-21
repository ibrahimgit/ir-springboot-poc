package com.ir.learning.springbootpoc.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedEexception extends AuthenticationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedEexception(String message) {
		super(message);
		this.message = message;
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
