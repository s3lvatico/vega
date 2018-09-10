package org.gmnz.vega.web.view;


import java.util.HashMap;
import java.util.Map;

import org.gmnz.vega.web.command.VegaCommand;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 08/09/2018.
 */
class VegaViewResolverFactory extends ViewResolverFactory {

	private static Map<String, Class<? extends ViewResolver>> viewResolversMap = new HashMap<>();

	static {
		viewResolversMap.put(VegaCommand.GET_FILE, VrGetFile.class);
	}



	protected VegaViewResolverFactory(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	public ViewResolver createViewResolver() {
		Class<? extends ViewResolver> viewResolverClass = viewResolversMap.get(requestContext.getCommandName());
		try {
			return viewResolverClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
