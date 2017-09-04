package com.ir.learning.springbootpoc.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.domainmodel.Student;

@RestController
@RequestMapping("bean")
public class BeanValidatonController {
	
	@RequestMapping(value = "getStudent", method=RequestMethod.POST)
	public Student getStudent(@Valid @RequestBody Student student) {
		return student;
	}
	

}
