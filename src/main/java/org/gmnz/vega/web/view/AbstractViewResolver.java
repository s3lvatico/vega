package org.gmnz.vega.web.view;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 11/09/2018.
 */
public abstract class AbstractViewResolver implements ViewResolver {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext servletContext;

	protected String VIEW_NAME;

	protected static final String FMT_JSP_ENV = "/WEB-INF/jsp/%s.jsp";



	public AbstractViewResolver(RequestContext requestContext, ResponseContext responseContext) {
		request = (HttpServletRequest) requestContext.getAttribute(RequestContext.ORIGINAL_REQUEST);
		servletContext = (ServletContext) requestContext.getAttribute(RequestContext.SERVLET_CONTEXT);
		response = (HttpServletResponse) responseContext.getAttribute(ResponseContext.ORIGINAL_RESPONSE);

		for (String attributeName : responseContext.getAttributeNames()) {
			request.setAttribute(attributeName, responseContext.getAttribute(attributeName));
		}
	}

	// TODO l'outcome va assolutamente gestito in fase di risoluzione della view


	protected void forward(String target) throws ServletException, IOException {
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}

}
