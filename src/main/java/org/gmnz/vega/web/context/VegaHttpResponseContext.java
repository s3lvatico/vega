package org.gmnz.vega.web.context;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * creato da simone in data 08/09/2018.
 */
class VegaHttpResponseContext implements ResponseContext {

	private final Map<String, String> parameters;
	private final Map<String, Object> attributes;



	VegaHttpResponseContext() {
		parameters = new HashMap<>();
		attributes = new HashMap<>();
		attributes.put(OUTCOME, 200);
	}



	@Override
	public int getOutcome() {
		return (Integer) attributes.get(ResponseContext.OUTCOME);
	}



	@Override
	public void setRequest(HttpServletRequest request) {
		attributes.put(ResponseContext.ORIGINAL_REQUEST, request);
	}



	@Override
	public void setResponse(HttpServletResponse response) {
		attributes.put(ResponseContext.ORIGINAL_RESPONSE, response);
	}



	@Override
	public void setParameter(String name, String value) {
		parameters.put(name, value);
	}



	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}



	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}



	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

}
