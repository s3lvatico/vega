package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 16/09/2018.
 */
class VrAllergenRedirect2GetAll extends AbstractViewResolver {

	public VrAllergenRedirect2GetAll(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
		isForward = false;
	}



	@Override
	protected String setViewName() {
		return "/app/allergen/getAll";
	}

}
