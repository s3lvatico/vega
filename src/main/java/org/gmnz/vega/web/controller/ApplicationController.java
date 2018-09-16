package org.gmnz.vega.web.controller;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * creato da simone in data 03/09/2018.
 */
public interface ApplicationController {

	// action management
	ResponseContext handleRequest(RequestContext requestContext);


	// view management
	void handleResponse(RequestContext requestContext, ResponseContext responseContext)
			throws ServletException, IOException;

}
