package org.gmnz.vega.web.context;


/**
 * creato da simone in data 03/09/2018.
 */
public interface RequestContext {

	String ORIGINAL_REQUEST = "original.request";
	String SERVLET_CONTEXT = "servlet.context";

	// void setRequest(HttpServletRequest request);

	String getParameter(String name);


	Object getAttribute(String name);


	public Object getFromSession(String name);


	String getCommandName();

}
