package com.ir.learning.springbootpoc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PocInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = Logger.getLogger(PocInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("Inside Prehandle");
		MDC.put("username", "Ibrahim");
		LOGGER.debug("after setting the MDC");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("removing the mdc in postHandle");
		MDC.remove("username");
		super.postHandle(request, response, handler, modelAndView);
	}

}
