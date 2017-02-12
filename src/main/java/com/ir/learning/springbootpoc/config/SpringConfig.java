package com.ir.learning.springbootpoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ir.learning.springbootpoc.interceptor.PocInterceptor;
import com.ir.learning.springbootpoc.services.PocService;
import com.ir.learning.springbootpoc.services.impl.PocServiceImpl;


@Configuration
public class SpringConfig extends WebMvcConfigurerAdapter {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(SpringConfig.class);
	
	
	@Bean
	public AsyncTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(3);
		pool.setMaxPoolSize(5);
		pool.setQueueCapacity(2);
		return pool;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getInterceptor()).addPathPatterns("/**");
		//super.addInterceptors(registry);
	}
	
	private HandlerInterceptor getInterceptor() {
		HandlerInterceptor interceptor = new PocInterceptor();
		return interceptor;
	}
	
	//public JavaMail

	@Bean
	@Profile("prod")
	public PocService pocService(){
		PocService pocService = new PocServiceImpl();
		return pocService;
	}
	
	
}
