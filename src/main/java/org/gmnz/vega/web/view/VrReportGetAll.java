package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


public class VrReportGetAll extends AbstractViewResolver {

	public VrReportGetAll(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	protected String setViewName() {
		return "reports";
	}


}
