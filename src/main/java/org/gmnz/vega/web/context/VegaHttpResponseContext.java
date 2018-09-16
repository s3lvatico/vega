package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * creato da simone in data 08/09/2018.
 */
class VegaHttpResponseContext extends AbstractContextObject implements ResponseContext {

	private Map<String, Object> sessionStorage;



	VegaHttpResponseContext() {
		sessionStorage = new HashMap<>();
		setAttribute(ContextProperty.STATUS_CODE, 200);
		setAttribute(ContextProperty.OUTCOME, "SUCCESS");
	}



	@Override
	public int getStatusCode() {
		return (Integer) getAttribute(ContextProperty.STATUS_CODE);
	}



	@Override
	public void setRequest(HttpServletRequest request) {
		setAttribute(ContextProperty.ORIGINAL_REQUEST, request);
	}



	@Override
	public void setResponse(HttpServletResponse response) {
		setAttribute(ContextProperty.ORIGINAL_RESPONSE, response);
	}



	@Override
	public void setSessionAttribute(String name, Object value) {
		sessionStorage.put(name, value);
	}



	@Override
	public Object getSessionAttribute(String name) {
		return sessionStorage.get(name);
	}



	@Override
	public String[] getSessionAttributeNames() {
		return sessionStorage.keySet().toArray(new String[]{});
	}


}
