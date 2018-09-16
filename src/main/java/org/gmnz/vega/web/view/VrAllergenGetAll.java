package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


public class VrAllergenGetAll extends AbstractViewResolver {

	public VrAllergenGetAll(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	protected String setViewName() {
		return "allergens";
	}


}
