package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * creato da simone in data 11/09/2018.
 */
public abstract class AbstractViewResolver implements ViewResolver {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext servletContext;



	public AbstractViewResolver(RequestContext requestContext, ResponseContext responseContext) {
		request = (HttpServletRequest) requestContext.getAttribute(RequestContext.ORIGINAL_REQUEST);
		servletContext = (ServletContext) requestContext.getAttribute(RequestContext.SERVLET_CONTEXT);
		response = (HttpServletResponse) responseContext.getAttribute(ResponseContext.ORIGINAL_RESPONSE);
	}



	protected void forward(String target) throws ServletException, IOException {
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}

}
