package com.ir.learning.springbootpoc.services;

import java.util.List;

import com.ir.learning.springbootpoc.domainmodel.Customer;

public interface MyServices {
	
	int doStuff(String str);
	
	void testAsync() throws InterruptedException;
	
	List<Customer> testDBTransctional(String lastName);

	//int update(int id, int age);

	//Customer save(Customer c);

	List<Customer> save(List<Customer> custList);

	List<Customer> findAllCustomers();

	int update(int id, String name, int age) throws Exception;

}
