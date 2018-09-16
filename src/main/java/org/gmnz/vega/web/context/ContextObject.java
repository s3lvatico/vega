package org.gmnz.vega.web.context;


public interface ContextObject {

	String[] getParameterNames();


	String[] getAttributeNames();


	String getParameter(String name);


	void setParameter(String name, String value);


	Object getAttribute(String name);


	void setAttribute(String name, Object value);

}