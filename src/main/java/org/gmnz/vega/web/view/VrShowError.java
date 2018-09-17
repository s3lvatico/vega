package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 15/09/2018.
 */
class VrShowError extends AbstractViewResolver {

	private static final String ERROR_VIEW_NAME = "showError";



	public VrShowError(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
		targetViewName = ERROR_VIEW_NAME;
	}



	@Override
	protected String setViewName() {
		return "showError";
	}

}
