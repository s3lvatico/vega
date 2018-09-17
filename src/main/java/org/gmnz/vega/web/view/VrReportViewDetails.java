package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


class VrReportViewDetails extends AbstractViewResolver {

	public VrReportViewDetails(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	protected String setViewName() {
		return "reportView";
	}

}
