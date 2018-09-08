package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * creato da simone in data 08/09/2018.
 */
class VegaHttpResponseContext implements ResponseContext {


	private final Map<String, String> parameters;
	private final Map<String, Object> attributes;



	VegaHttpResponseContext() {
		parameters = new HashMap<>();
		attributes = new HashMap<>();
	}



	@Override
	public int getOutcome() {
		return (Integer) attributes.get("OUTCOME");
	}



	@Override
	public void setRequest(HttpServletRequest request) {
		attributes.put("request", request);
	}



	@Override
	public void setResponse(HttpServletResponse response) {
		attributes.put("response", response);
	}



	@Override
	public void setParameter(String name, String value) {
		parameters.put(name, value);
	}



	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

}
