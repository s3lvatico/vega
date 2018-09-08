package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;
import org.gmnz.vega.web.context.ResponseContextFactory;


/**
 * creato da simone in data 08/09/2018.
 */
abstract class AbstractVegaCommand implements Command {

	protected ResponseContext responseContext;


	AbstractVegaCommand(RequestContext requestContext) {
		responseContext = ResponseContextFactory.getFactory().createResponseContext();
		initialize(requestContext);
	}



	protected abstract void initialize(RequestContext requestContext);

}
