package org.gmnz.vega.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;

import org.gmnz.vega.web.command.Command;
import org.gmnz.vega.web.command.CommandFactory;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;
import org.gmnz.vega.web.view.ViewResolver;
import org.gmnz.vega.web.view.ViewResolverFactory;


class WebApplicationController implements ApplicationController {

	@Override
	public ResponseContext handleRequest(RequestContext requestContext) {
		// TODO Auto-generated method stub
		/*
		 * chiama la command factory crea il comando sulla base del nome (sta nel
		 * requestContext) inietta nel comando i parametri esegui il comando
		 */
		CommandFactory commandFactory = CommandFactory.getFactory();
		Command command = commandFactory.createCommand(requestContext);

		return command.execute();
	}



	@Override
	public void handleResponse(RequestContext requestContext, ResponseContext responseContext)
			throws ServletException, IOException {

		ViewResolverFactory viewResolverFactory = ViewResolverFactory.getFactory(requestContext, responseContext);
		ViewResolver viewResolver = viewResolverFactory.createViewResolver();
		viewResolver.resolveToView(requestContext, responseContext);

	}

}
