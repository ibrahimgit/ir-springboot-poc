package com.ir.learning.springbootpoc.mapper;

import java.util.concurrent.Callable;

public interface MyJob {
	
	public Callable<Boolean> job(String str);

}

