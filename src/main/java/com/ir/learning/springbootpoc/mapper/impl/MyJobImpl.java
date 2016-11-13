package com.ir.learning.springbootpoc.mapper.impl;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ir.learning.springbootpoc.mapper.MyJob;
import com.ir.learning.springbootpoc.services.MyServices;

@Service
public class MyJobImpl implements MyJob {
	
	@Autowired
	private MyServices myService;

	@Override
	public Callable<Boolean> job(String str) {
		return new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				myService.doStuff(str);
				Thread.sleep(1000);
				return Boolean.TRUE;
			}
		};
	}

}
