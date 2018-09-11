package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.ContextObject;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 08/09/2018.
 */
public abstract class ViewResolverFactory {

	protected RequestContext requestContext;
	protected ContextObject responseContext;



	public static ViewResolverFactory getFactory(RequestContext requestContext, ContextObject responseContext) {
		return new VegaViewResolverFactory(requestContext, responseContext);
	}



	protected ViewResolverFactory(RequestContext requestContext, ContextObject responseContext) {
		this.requestContext = requestContext;
		this.responseContext = responseContext;
	}



	public abstract ViewResolver createViewResolver();

}
