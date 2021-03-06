package com.ir.learning.springbootpoc.controller;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.mapper.ProducerEngine;

@RestController
@RequestMapping("producer")
public class ProducerController {
	
	//private static final Logger LOGGER = Logger.getLogger(ProducerController.class);
	private static final Log LOGGER = LogFactory.getLog(ProducerController.class); // Using JCL framework
	
	@Autowired
	private ProducerEngine producerEngine;
	
	
	@RequestMapping(value="produce", method=RequestMethod.GET)
	public Boolean refreshCache() {
		LOGGER.debug("Producer produces messages");
		boolean response = producerEngine.produce(Arrays.asList("Ibrahim", "Rashid", "learning"));
		LOGGER.debug("Produce completed ... " + response);
		return response;
	}

}
