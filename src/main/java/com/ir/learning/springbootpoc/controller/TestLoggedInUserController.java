package com.ir.learning.springbootpoc.controller;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ir.learning.springbootpoc.domainmodel.LoggedInUser;

@RestController
@RequestMapping("loggedinuser")
public class TestLoggedInUserController {
	
	
	@RequestMapping(value = "principal", method=RequestMethod.GET)
	public LoggedInUser getLoggedInUser(Principal principal) {
		LoggedInUser user = new LoggedInUser();
		user.setUsername(principal.getName());
		
		return user;
	}
	
	@RequestMapping(value = "authenticateduser", method=RequestMethod.GET)
	//@PreAuthorize("hasRole('LOVE')") === @PreAuthorize("hasAuthority('ROLE_LOVE')") if role being set using roles(string)
	@PreAuthorize("hasAuthority('ISU')") //@PreAuthorize("hasRole('ISU')") if role is set through authorities(string)
	public LoggedInUser getAuthenticatedUser(Authentication authentication) {
		LoggedInUser user = new LoggedInUser();
		user.setUsername(authentication.getName());
		user.setRoles(authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return user;
	}
	
	@RequestMapping(value = "securitycontext", method=RequestMethod.GET)
	public LoggedInUser login() {
		LoggedInUser user = new LoggedInUser();
		user.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setRoles(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return user;
	}
	
	@RequestMapping(value = "authenticatedPrincipal", method=RequestMethod.GET)
	//@PreAuthorize("hasRole('USER')") === @PreAuthorize("hasAuthority('ROLE_USER')") if role being set using roles(string)
	@PreAuthorize("hasAuthority('AH')")
	public LoggedInUser authenticatedPrinical(@AuthenticationPrincipal User user) {
		LoggedInUser loggedInUser = new LoggedInUser();
		loggedInUser.setUsername(user.getUsername());
		loggedInUser.setRoles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return loggedInUser;
	}

}
