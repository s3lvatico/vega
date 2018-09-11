package org.gmnz.vega.web.view;


import org.gmnz.vega.web.command.VegaCommand;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


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
			final Constructor<? extends ViewResolver> constructor = viewResolverClass.getConstructor(RequestContext.class, ResponseContext.class);
			return constructor.newInstance(requestContext, responseContext);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

}
