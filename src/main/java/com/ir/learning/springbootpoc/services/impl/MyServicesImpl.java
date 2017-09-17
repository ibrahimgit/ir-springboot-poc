package com.ir.learning.springbootpoc.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ir.learning.springbootpoc.domainmodel.Customer;
import com.ir.learning.springbootpoc.repository.CustomerRepository;
import com.ir.learning.springbootpoc.services.MyServices;

@Service
public class MyServicesImpl implements MyServices{
	
	private static final Log LOGGER = LogFactory.getLog(MyServicesImpl.class); //using JCL framework 
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public int doStuff(String str) {
		LOGGER.debug("This is a test message " + str);
		//Logic for pulling the data from Hippo and sending it to Kafka topic through producer
		return 0;
	}

	@Override
	@Async
	public void testAsync() throws InterruptedException {
		LOGGER.debug("Testing aync call...");
		Thread currentThread = Thread.currentThread();
		LOGGER.debug("Before going into sleep mode");
		//Thread.sleep(10000l);
		LOGGER.debug("Woke up from deep slumber");
		
	}

	@Override
	public List<Customer> testDBTransctional(String lastName) {
		return customerRepository.findByLastName(lastName);
	}
	
	
	@Override
	@Transactional//(rollbackOn = Exception.class)
	public int update(int id, String name, int age) throws Exception {
		int count = customerRepository.updateCustomerAge(id, age);
		if(age > 100) {
			throw new IncorrectResultSizeDataAccessException(5);
		}
		
		if(age > 50) {
			throw new Exception("Testing DB transaction");
		}
		customerRepository.updateCustomerLastName(id, name);
		return count;
	}
	
	@Override
	public List<Customer> save(List<Customer> custList) {
		List<Customer> customerList = new ArrayList<>();
		for (Customer customer : custList) {
			customerList.add(customerRepository.save(customer));
		}
		return customerList;
	}

	@Override
	public List<Customer> findAllCustomers() {
		Iterable<Customer> customers = customerRepository.findAll();
		List<Customer> list = new ArrayList<>();
		customers.forEach(customer -> list.add(customer));
		return list;
	}

}
