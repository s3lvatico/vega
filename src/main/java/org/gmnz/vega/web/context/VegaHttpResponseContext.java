package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * creato da simone in data 08/09/2018.
 */
class VegaHttpResponseContext extends AbstractContextObject implements ResponseContext {

	VegaHttpResponseContext() {
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




}
