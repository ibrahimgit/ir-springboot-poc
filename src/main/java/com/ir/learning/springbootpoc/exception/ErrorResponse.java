package com.ir.learning.springbootpoc.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	private boolean hasError;
	private String message;
	private HttpStatus status;
	private int errorCode;
	
	public ErrorResponse(boolean hasError, String message, HttpStatus status, int errorCode) {
		this.hasError = hasError;
		this.message = message;
		this.status = status;
		this.errorCode = errorCode;
		
	}
	
	
	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
