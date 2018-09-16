package org.gmnz.vega.ui.web;


import javax.servlet.*;
import java.io.IOException;


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
