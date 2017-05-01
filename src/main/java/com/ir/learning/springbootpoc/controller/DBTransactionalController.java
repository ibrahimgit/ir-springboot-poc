package com.ir.learning.springbootpoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.domainmodel.Customer;
import com.ir.learning.springbootpoc.services.MyServices;

@RestController
@RequestMapping("transaction")
public class DBTransactionalController {
	
	private static final Logger LOGGER = Logger.getLogger(DBTransactionalController.class);
	
	@Autowired
	private MyServices myServices;
	
	//@Autowired
	//private JpaTransactionManager transactionManager;
	
	//@Autowired
	//private PlatformTransactionManager transactionManager;
	
	@RequestMapping("find/{lastName}")
	public List<Customer> testTransactional(@PathVariable("lastName") String lastName) {
		LOGGER.debug("inside testTransactional");
		return myServices.testDBTransctional(lastName);
		
	}
	
	@RequestMapping("customers")
	public List<Customer> findAll() {
		LOGGER.debug("inside findAll");
		return myServices.findAllCustomers();
		
	}
	
	@RequestMapping("update/{id}")
	public String update(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("age") int age) throws Exception {
		LOGGER.debug("inside update");
		int count = myServices.update(id, name, age);
		return "successfully update " + count + " records";
		
	}
	
	
	@RequestMapping("insert")
	public List<Customer> insert() {
		LOGGER.debug("inside insert");
		Customer customer = new Customer("I", "R", 20);
		Customer customer2 = new Customer("II", "RR", 30);
		Customer customer3 = new Customer("III", "RRR", 40);
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		customerList.add(customer2);
		customerList.add(customer3);
		return myServices.save(customerList);
		
	}

}
