package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 16/09/2018.
 */
class VrAllergenEdit extends AbstractViewResolver {

	public VrAllergenEdit(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	protected String setViewName() {
		return "allergenManagement";
	}

}
