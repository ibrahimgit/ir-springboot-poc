package com.ir.learning.springbootpoc.controller;

import org.apache.log4j.Logger;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ir.learning.springbootpoc.exception.ErrorResponse;

//@RestController
@ControllerAdvice
public class ErrorHandlingController {

	private static final Logger LOGGER = Logger.getLogger(ErrorHandlingController.class);

	@ExceptionHandler({IncorrectResultSizeDataAccessException.class, NullPointerException.class})
	public ResponseEntity<ErrorResponse> error(Exception e) {
		LOGGER.error(e.getMessage(), e);
		ErrorResponse er = new ErrorResponse(true, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());

		ResponseEntity<ErrorResponse> re = new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);

		return re;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> beanValidationError(MethodArgumentNotValidException e) {
		ErrorResponse er = new ErrorResponse(true, e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());

		ResponseEntity<ErrorResponse> re = new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);

		return re;
	}

}
