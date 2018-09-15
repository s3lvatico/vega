package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.ContextProperty;
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

	protected static final String FMT_JSP_ENV = "/WEB-INF/jsp/%s.jsp";
	protected String targetViewName;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext servletContext;



	public AbstractViewResolver(RequestContext requestContext, ResponseContext responseContext) {
		request = (HttpServletRequest) requestContext.getAttribute(ContextProperty.ORIGINAL_REQUEST);
		servletContext = (ServletContext) requestContext.getAttribute(ContextProperty.SERVLET_CONTEXT);
		response = (HttpServletResponse) responseContext.getAttribute(ContextProperty.ORIGINAL_RESPONSE);

		targetViewName = setViewName();

		for (String attributeName : responseContext.getAttributeNames()) {
			request.setAttribute(attributeName, responseContext.getAttribute(attributeName));
		}
	}



	protected abstract String setViewName();



	protected void forward(String target) throws ServletException, IOException {
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}



	public void resolveToView(RequestContext requestContext, ResponseContext responseContext) throws ServletException, IOException {
		String target = String.format(FMT_JSP_ENV, targetViewName);
		forward(target);
	}

}
