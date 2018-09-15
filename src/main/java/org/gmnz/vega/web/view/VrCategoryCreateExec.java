package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 15/09/2018.
 */
public class VrCategoryCreateExec extends AbstractViewResolver {

	public VrCategoryCreateExec(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
		isForward = false;
	}



	@Override
	protected String setViewName() {
		return "/app/category/getAll";
	}

}
