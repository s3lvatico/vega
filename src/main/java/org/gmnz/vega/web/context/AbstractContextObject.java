package org.gmnz.vega.web.context;


import java.util.HashMap;
import java.util.Map;


abstract class AbstractContextObject implements ContextObject {

	protected final Map<String, String> parameters;
	protected final Map<String, Object> attributes;



	public AbstractContextObject() {
		parameters = new HashMap<>();
		attributes = new HashMap<>();
	}



	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}



	@Override
	public void setParameter(String name, String value) {
		parameters.put(name, value);
	}



	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}



	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

}
