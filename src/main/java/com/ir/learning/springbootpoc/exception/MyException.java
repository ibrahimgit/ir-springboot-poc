package com.ir.learning.springbootpoc.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyException() {
		
	}
	
	public MyException(String message) {
		super(message);
	}
	
	public MyException(String message, Throwable e) {
		super(message, e);
	}
	
	

}
