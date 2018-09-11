package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * creato da simone in data 03/09/2018.
 */
public interface ResponseContext extends ContextObject {

	String ORIGINAL_REQUEST = "responsecontext.original.request";
	String ORIGINAL_RESPONSE = "responsecontext.original.response";

	String OUTCOME = "responsecontext.processing.outcome";

	String ERROR_MESSAGE = "responsecontext.error.message";



	int getOutcome();



	void setRequest(HttpServletRequest request);



	void setResponse(HttpServletResponse response);

}
