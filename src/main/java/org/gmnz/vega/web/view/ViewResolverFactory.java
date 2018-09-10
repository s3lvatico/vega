package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 08/09/2018.
 */
public abstract class ViewResolverFactory {

	protected RequestContext requestContext;
	protected ResponseContext responseContext;



	public static ViewResolverFactory getFactory(RequestContext requestContext, ResponseContext responseContext) {
		return new VegaViewResolverFactory(requestContext, responseContext);
	}



	protected ViewResolverFactory(RequestContext requestContext, ResponseContext responseContext) {
		this.requestContext = requestContext;
		this.responseContext = responseContext;
	}



	public abstract ViewResolver createViewResolver();

}
