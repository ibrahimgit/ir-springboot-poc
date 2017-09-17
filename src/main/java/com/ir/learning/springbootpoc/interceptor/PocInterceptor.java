package com.ir.learning.springbootpoc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PocInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log LOGGER = LogFactory.getLog(PocInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("Inside Prehandle - URI: " + request.getRequestURI());
		MDC.put("username", "Ibrahim");
		LOGGER.debug("after setting the MDC");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.debug("removing the mdc in postHandle - URI: " + request.getRequestURI());
		MDC.remove("username");
		super.postHandle(request, response, handler, modelAndView);
	}

}
