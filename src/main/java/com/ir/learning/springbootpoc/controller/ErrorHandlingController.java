package com.ir.learning.springbootpoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.exception.ErrorResponse;
import com.ir.learning.springbootpoc.exception.MyException;

@RestController
@ControllerAdvice
public class ErrorHandlingController {


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> error(Exception e) throws MyException{
		ErrorResponse er = new ErrorResponse(true, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());

		ResponseEntity<ErrorResponse> re = new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);

		return re;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> beanValidationError(MethodArgumentNotValidException e) throws MyException{
		ErrorResponse er = new ErrorResponse(true, e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());

		ResponseEntity<ErrorResponse> re = new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);

		return re;
	}

}
