package com.ir.learning.springbootpoc.controller;

import java.io.IOException;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.exception.MyException;

@RestController
public class TestExceptionController {
	
	
	@RequestMapping("nullPointerException")
	public void nullPointerException() {
		throw new NullPointerException("I am NPE");
	}
	
	
	@RequestMapping("dataAccessException")
	public void incorrectResultSizeDataAccessException() {
		throw new IncorrectResultSizeDataAccessException("I am DAE", 1);
	}
	
	
	@RequestMapping("iOException")
	public void iOException() throws IOException {
		throw new IOException("I am IOE");
	}
	
	@RequestMapping("myException")
	public void myException() throws MyException {
		throw new MyException("I am custom exception");
	}

}
