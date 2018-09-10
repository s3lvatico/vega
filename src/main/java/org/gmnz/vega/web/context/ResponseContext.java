package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * creato da simone in data 03/09/2018.
 */
public interface ResponseContext {

	String ORIGINAL_REQUEST = "original.request";
	String ORIGINAL_RESPONSE = "original.response";



	int getOutcome();



	void setRequest(HttpServletRequest request);



	void setResponse(HttpServletResponse response);



	void setParameter(String name, String value);



	void setAttribute(String name, Object value);



	String getParameter(String name);



	Object getAttribute(String name);

}
