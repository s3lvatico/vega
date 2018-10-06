package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 07/10/2018.
 */
class VrUsersRedirect2GetAll extends AbstractViewResolver {

	public VrUsersRedirect2GetAll(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
		isForward = false;
	}



	@Override
	protected String setViewName() {
		return "/app/users/getAll";
	}

}
