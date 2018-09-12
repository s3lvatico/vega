package org.gmnz.vega.web.view;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
		viewResolversMap.put(VegaCommand.Category.GET_ALL, VrCategoryGetAll.class);
	}



	protected VegaViewResolverFactory(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	public ViewResolver createViewResolver() {
		Class<? extends ViewResolver> viewResolverClass = viewResolversMap.get(requestContext.getCommandName());
		// TODO l'outcome nel response context va assolutamente gestito in fase di
		// risoluzione della view
		try {
			final Constructor<? extends ViewResolver> constructor = viewResolverClass.getConstructor(RequestContext.class,
					ResponseContext.class);
			return constructor.newInstance(requestContext, responseContext);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// TODO
		return null;
	}

}
