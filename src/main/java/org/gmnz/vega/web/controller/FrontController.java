package org.gmnz.vega.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.RequestContextFactory;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 03/09/2018.
 */
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = -3718912040641304794L;



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
		ApplicationControllerFactory acFactory = ApplicationControllerFactory.getFactory();
		ApplicationController appController = acFactory.createApplicationController(req);
		// TODO vedi se è necessario init dell'appController
		
		RequestContextFactory requestContextFactory = RequestContextFactory.getFactory();
		RequestContext requestContext = requestContextFactory.createContext(req);

		ResponseContext responseContext = appController.handleRequest(requestContext);
		responseContext.setResponse(resp);
		
		appController.handleResponse(requestContext, responseContext);
		
		// TODO vedi se è necessario il destroy dell'appController
	}

}
