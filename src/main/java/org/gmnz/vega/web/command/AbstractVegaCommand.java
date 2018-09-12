package org.gmnz.vega.web.command;


import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;
import org.gmnz.vega.web.context.ResponseContextFactory;


/**
 * creato da simone in data 08/09/2018.
 */
abstract class AbstractVegaCommand implements Command {

	protected ResponseContext model;



	public AbstractVegaCommand(RequestContext requestContext) {
		model = ResponseContextFactory.getFactory().createResponseContext();
		HttpServletRequest httpRequest = (HttpServletRequest) requestContext.getAttribute(RequestContext.ORIGINAL_REQUEST);
		model.setAttribute("contextRoot", httpRequest.getContextPath());
		initialize(requestContext);
	}



	@Override
	public final ResponseContext execute() {
		process();
		return model;
	}



	protected abstract void initialize(RequestContext requestContext);



	/**
	 * esegue il comando e popola adeguatamente il {@link #model}
	 */
	protected abstract void process();

}
