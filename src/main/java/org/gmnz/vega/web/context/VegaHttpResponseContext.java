package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * creato da simone in data 08/09/2018.
 */
class VegaHttpResponseContext extends AbstractContextObject implements ResponseContext {

	VegaHttpResponseContext() {
		setAttribute(OUTCOME, 200);
	}



	@Override
	public int getOutcome() {
		return (Integer) getAttribute(ResponseContext.OUTCOME);
	}



	@Override
	public void setRequest(HttpServletRequest request) {
		setAttribute(ResponseContext.ORIGINAL_REQUEST, request);
	}



	@Override
	public void setResponse(HttpServletResponse response) {
		setAttribute(ResponseContext.ORIGINAL_RESPONSE, response);
	}




}
