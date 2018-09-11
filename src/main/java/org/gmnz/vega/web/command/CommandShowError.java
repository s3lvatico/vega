package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


class CommandShowError extends AbstractVegaCommand {

	CommandShowError(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		responseContext.setAttribute(ResponseContext.OUTCOME, requestContext.getAttribute(RequestContext.STATUS_CODE));
		responseContext.setParameter(ResponseContext.ERROR_MESSAGE,
				requestContext.getParameter(RequestContext.ERROR_MESSAGE));
	}



	@Override
	public ResponseContext execute() {
		return responseContext;
	}

}
