package com.ir.learning.springbootpoc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.domainmodel.Login;
import com.ir.learning.springbootpoc.jwt.JwtTokenManager;

@RestController
public class AuthenticationController {
	
	private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenManager manager;
	
	@RequestMapping(value="authenticate",method = RequestMethod.POST)
	public ResponseEntity<String> authenticate(@Valid @RequestBody Login login) {
		
		LOGGER.debug("Authenticating the user {}", login.getUsername());
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		Authentication authenticationn = authenticationManager.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(authenticationn);
		LOGGER.debug("User principal object is {}", UserDetailsService.class.isAssignableFrom(authenticationn.getPrincipal().getClass()));
		List<String> roles = authenticationn.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		String jwtToken = manager.createJWTToken(authentication.getName(), roles);
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
		ResponseEntity<String> response = new ResponseEntity<String>(header, HttpStatus.OK);
		return response;
		
	}

}
