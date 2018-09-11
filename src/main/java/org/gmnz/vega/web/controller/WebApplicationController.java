package org.gmnz.vega.web.controller;


import java.io.IOException;

import javax.servlet.ServletException;

import org.gmnz.vega.web.command.Command;
import org.gmnz.vega.web.command.CommandFactory;
import org.gmnz.vega.web.context.ContextObject;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;
import org.gmnz.vega.web.view.ViewResolver;
import org.gmnz.vega.web.view.ViewResolverFactory;


class WebApplicationController implements ApplicationController {

	@Override
	public ResponseContext handleRequest(RequestContext requestContext) {
		CommandFactory commandFactory = CommandFactory.getFactory();
		Command command = commandFactory.createCommand(requestContext);

		return command.execute();
	}



	@Override
	public void handleResponse(RequestContext requestContext, ContextObject responseContext)
			throws ServletException, IOException {

		ViewResolverFactory viewResolverFactory = ViewResolverFactory.getFactory(requestContext, responseContext);
		ViewResolver viewResolver = viewResolverFactory.createViewResolver();
		viewResolver.resolveToView(requestContext, responseContext);

	}

}
