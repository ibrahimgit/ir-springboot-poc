package com.ir.learning.springbootpoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.domainmodel.Galaxy;
import com.ir.learning.springbootpoc.exception.ErrorResponse;
import com.ir.learning.springbootpoc.exception.MyException;
import com.ir.learning.springbootpoc.services.MyServices;
import com.ir.learning.springbootpoc.services.PocService;

@RestController
@RequestMapping("poc")
public class PocController {

	@Autowired(required=false)
	private PocService pocService;
	
	@Autowired
	private MyServices myServices;
	
	private static final String CUSTOM_EXCEPTION = "Custom Exception: ";

	//private static final Logger LOGGER = Logger.getLogger(PocController.class); //using log4j
	//private static final Log LOGGER = LogFactory.getLog(PocController.class); //using JCL
	private static final Logger LOGGER = LoggerFactory.getLogger(PocController.class); //using logback with slf4j

	@RequestMapping(value = "exception/{id}", method = RequestMethod.GET)
	public Galaxy testException(@PathVariable("id") Integer id) throws MyException{
		if( id == 0) {
			Galaxy galaxy = new Galaxy();
			galaxy.setName("Andromeda");
			galaxy.setDistanceFromEarth(250000l);
			galaxy.setLength(200l);
			galaxy.setBreadth(45l);
			return galaxy;
		}

		throw new MyException("I am thrown away");
	}


	@ExceptionHandler(MyException.class)
	public ResponseEntity<ErrorResponse> exception(MyException e) {
		ErrorResponse er = new ErrorResponse(true, CUSTOM_EXCEPTION + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());

		ResponseEntity<ErrorResponse> re = new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);

		return re;
	}
	
	@RequestMapping("testAsync")
	public String testAsync() throws InterruptedException {
		LOGGER.debug("Inside test Async");
		myServices.testAsync();
		LOGGER.debug("exiting test Async");
		return "success";
	}
	
	
	@RequestMapping("testparam")
	public String testParam(String param) throws InterruptedException {
		LOGGER.debug("Inside test param: " + param);
		return "success";
	}
	
	@RequestMapping("path/{testpath}")
	public String testPathvariable(String testpath) throws InterruptedException {
		LOGGER.debug("Inside test path: " + testpath);
		return "success";
	}
	
	

}
