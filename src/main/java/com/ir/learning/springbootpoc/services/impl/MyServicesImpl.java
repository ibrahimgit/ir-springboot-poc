package com.ir.learning.springbootpoc.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ir.learning.springbootpoc.services.MyServices;

@Service
public class MyServicesImpl implements MyServices{
	
	private static final Logger LOGGER = Logger.getLogger(MyServicesImpl.class);
	
	@Override
	public int doStuff(String str) {
		LOGGER.debug("This is a test message " + str);
		//Logic for pulling the data from Hippo and sending it to Kafka topic through producer
		return 0;
	}

}
