package com.ir.learning.springbootpoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.exception.ErrorResponse;
import com.ir.learning.springbootpoc.exception.MyException;

@RestController
public class ErrorController {
	

	@RequestMapping(value = "error", method = RequestMethod.GET)
	public ResponseEntity<ErrorResponse> error(Exception e) throws MyException{
		ErrorResponse er = new ErrorResponse(true, "This is a /error exception", HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());

		ResponseEntity<ErrorResponse> re = new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);

		return re;
	}



}
