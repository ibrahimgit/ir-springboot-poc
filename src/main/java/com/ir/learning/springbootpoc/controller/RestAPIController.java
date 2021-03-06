package com.ir.learning.springbootpoc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {
	
	private static final Log LOGGER = LogFactory.getLog(RestAPIController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String testRootUri(@RequestParam String param) throws InterruptedException {
		LOGGER.debug("Inside test path: " + param);
		return "success";
	}

}
