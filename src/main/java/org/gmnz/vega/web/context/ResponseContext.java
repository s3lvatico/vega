package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * creato da simone in data 03/09/2018.
 */
public interface ResponseContext extends ContextObject {


	int getStatusCode();


	void setRequest(HttpServletRequest request);


	void setResponse(HttpServletResponse response);


	void setSessionAttribute(String name, Object value);


	Object getSessionAttribute(String name);


	String[] getSessionAttributeNames();

}
