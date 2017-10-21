package com.ir.learning.springbootpoc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.HttpMethodConstraintElement;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.ir.learning.springbootpoc.exception.UnauthorizedEexception;
import com.ir.learning.springbootpoc.jwt.JwtTokenManager;

import io.jsonwebtoken.JwtException;

public class CustomJwtAuthenticationFilter extends GenericFilterBean {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomJwtAuthenticationFilter.class);
	private static final String AUTHENTICATE_URL = "/authenticate";
	
	private JwtTokenManager jwtTokenManager;
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	public CustomJwtAuthenticationFilter(JwtTokenManager jwtTokenManager, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
		this.jwtTokenManager = jwtTokenManager;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		if(isAuthentcateUrl(req)) {
			LOGGER.debug("skipping this filter for authenticate URL");
			chain.doFilter(request, response);
		} else {

			String authroizationHeader = req.getHeader(HttpHeaders.AUTHORIZATION);

			if(authroizationHeader == null || authroizationHeader.isEmpty() || !authroizationHeader.startsWith("Bearer ")) {
				LOGGER.debug("Authorization header is missing or empty");
				//res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//, "Authorization header is missing or empty");
				//res.getOutputStream().println("Authorization header is missing or empty");
				//restAuthenticationEntryPoint.commence(req, res, new UnauthorizedEexception("Authorization header is missing or empty"));
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or empty");
			} else {

				String jwt = getJWT(authroizationHeader);
				try {
					if(jwtTokenManager.validateJwtToken(jwt)) {
						Authentication authentication = jwtTokenManager.getAuthentication(jwt);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
					chain.doFilter(request, response);
				} catch(JwtException | AuthenticationException e) {
					res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());

				}
			}
		}
		
	}

	private boolean isAuthentcateUrl(HttpServletRequest request) {
		LOGGER.debug("Request URI is {}", request.getRequestURI());
		String uriPath = new UrlPathHelper().getPathWithinApplication(request);
		LOGGER.debug("URI is {}", uriPath);
		return AUTHENTICATE_URL.equals(uriPath) && request.getMethod().equalsIgnoreCase(request.getMethod());
	}

	private String getJWT(String authroizationHeader) {
		return authroizationHeader.substring(7);
	}

}
