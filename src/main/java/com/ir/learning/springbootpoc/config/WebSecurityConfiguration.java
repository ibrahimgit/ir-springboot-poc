package com.ir.learning.springbootpoc.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;


//@Configuration
public class WebSecurityConfiguration /*extends GlobalAuthenticationConfigurerAdapter*/ {
	
	//@Autowired
	private UserDetailsService userDetailsService;
	
	//@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

}
