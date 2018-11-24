package org.gmnz.vega.web.controller;


import org.gmnz.vega.web.command.Command;
import org.gmnz.vega.web.command.CommandFactory;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;
import org.gmnz.vega.web.view.ViewResolver;
import org.gmnz.vega.web.view.ViewResolverFactory;

import javax.servlet.ServletException;
import java.io.IOException;


class WebApplicationController implements ApplicationController {

	private CommandFactory commandFactory;



	WebApplicationController() {
		commandFactory = CommandFactory.getFactory();
	}



	@Override
	public ResponseContext handleRequest(RequestContext requestContext) {
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
