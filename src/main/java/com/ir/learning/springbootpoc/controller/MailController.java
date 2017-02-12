package com.ir.learning.springbootpoc.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	
	//JavaMai
	
	@RequestMapping(value="sendMessage", method = RequestMethod.GET, produces = "Application/JSON")
	public Map<String, String> sendMessage() {
		return null;
		
	}

}
