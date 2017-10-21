package com.ir.learning.springbootpoc.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomDaoAuthenticationProvider.class);

	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		//do nothing
		LOGGER.debug("skipping the additional authentication check");
	}
	

}
