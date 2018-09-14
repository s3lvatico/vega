package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;

// TODO l'outcome va assolutamente gestito in fase di risoluzione della view





class CommandShowError extends AbstractVegaCommand {

	CommandShowError(RequestContext requestContext) {

		super(requestContext);

	}




	@Override
	protected void initialize(RequestContext requestContext) {

		model.setAttribute(ContextProperty.STATUS_CODE, requestContext.getAttribute(ContextProperty.STATUS_CODE));
		model.setParameter(ContextProperty.ERROR_MESSAGE,
				requestContext.getParameter(ContextProperty.ERROR_MESSAGE));

	}




	@Override
	protected void process() {

	}

}
