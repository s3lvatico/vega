package org.gmnz.vega.ui.web;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {

	private String defaultEncoding;
	private String targetEncoding;



	@Override
	public void init(FilterConfig filterConfig) {
		defaultEncoding = filterConfig.getInitParameter("defaultEncoding");
		targetEncoding = filterConfig.getInitParameter("targetEncoding");
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * String requestCharacterEncoding = request.getCharacterEncoding(); if
		 * (requestCharacterEncoding == null) { reEncodeRequestParameters(request); }
		 */
		request.setCharacterEncoding(targetEncoding);
		chain.doFilter(request, response);
	}



	@SuppressWarnings("unused")
	@Deprecated
	private void reEncodeRequestParameters(ServletRequest request) throws UnsupportedEncodingException {
		Map<String, String> parametersMap = new HashMap<>();
		String paramName;
		String paramValue;

		while (request.getParameterNames().hasMoreElements()) {
			paramName = request.getParameterNames().nextElement();
			paramValue = request.getParameter(paramName);
			paramValue = new String(paramValue.getBytes(defaultEncoding), targetEncoding);
			parametersMap.put(paramName, paramValue);
		}
		request.setAttribute("parametersMap", parametersMap);
	}



	@Override
	public void destroy() {

	}
}
