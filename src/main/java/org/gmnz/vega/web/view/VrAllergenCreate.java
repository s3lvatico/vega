package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 15/09/2018.
 */
public class VrAllergenCreate extends AbstractViewResolver {

	public VrAllergenCreate(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	protected String setViewName() {
		return "allergenManagement";
	}


}
