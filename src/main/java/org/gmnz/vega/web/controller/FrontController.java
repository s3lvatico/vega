package org.gmnz.vega.web.controller;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.RequestContextFactory;
import org.gmnz.vega.web.context.ResponseContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * creato da simone in data 03/09/2018.
 */
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = -3718912040641304794L;

	private ApplicationControllerFactory applicationControllerFactory;
	private RequestContextFactory requestContextFactory;



	@Override
	public void init() throws ServletException {
		applicationControllerFactory = ApplicationControllerFactory.getFactory();
		requestContextFactory = RequestContextFactory.getFactory();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}



	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ApplicationController appController = applicationControllerFactory.createApplicationController(req);

		RequestContext requestContext = requestContextFactory.createContext(req);

		ResponseContext responseContext = appController.handleRequest(requestContext);

		responseContext.setResponse(resp);

		appController.handleResponse(requestContext, responseContext);
	}

}
