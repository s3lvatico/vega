package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;

// TODO l'outcome va assolutamente gestito in fase di risoluzione della view

class CommandShowError extends AbstractVegaCommand {

	CommandShowError(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		model.setAttribute(ResponseContext.OUTCOME, requestContext.getAttribute(RequestContext.STATUS_CODE));
		model.setParameter(ResponseContext.ERROR_MESSAGE,
				requestContext.getParameter(RequestContext.ERROR_MESSAGE));
	}



	@Override
	protected void process() {}

}
