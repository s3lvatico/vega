package org.gmnz.vega.ui.web;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {

//	private String defaultEncoding;
	private String targetEncoding;



	@Override
	public void init(FilterConfig filterConfig) {
//		defaultEncoding = filterConfig.getInitParameter("defaultEncoding");
		targetEncoding = filterConfig.getInitParameter("targetEncoding");
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(targetEncoding);
		chain.doFilter(request, response);
	}



	@Override
	public void destroy() {
		// nessuna operazione necessaria
	}

}
