package com.ir.learning.springbootpoc.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;


@Component
public class ProducerEngine {
	
	@Autowired
	private AsyncTaskExecutor asyncTaskExecutor;
	
	@Autowired
	private MyJob mapper;
	
	public Boolean produce(List<String> cusomerList) {
		
		Set<Future<Boolean>> futureTaskList = new HashSet<>();
		for(String str : cusomerList) {
			System.out.println("Creating threads..... " + str);
			futureTaskList.add(asyncTaskExecutor.submit(mapper.job(str)));
			System.out.println("Threads created.....");
		}
		
		System.out.println("All Threads created.... " + cusomerList.size());
		
		for(Future<Boolean> futureTask : futureTaskList) {
			try {
				System.out.println("iterating the loop");
				boolean value = futureTask.get();
				System.out.println("Reponse: " + value);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return Boolean.FALSE;
			}
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("*****called when program is terminated.....");
			}
		});
		
		System.out.println("returning from ProducerEndine" + cusomerList.size());
		return Boolean.TRUE;
		
	}

}
