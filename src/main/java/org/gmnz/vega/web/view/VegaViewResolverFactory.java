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
		viewResolversMap.put(VegaCommand.Category.GET_ALL, VrCategoryGetAll.class);
		viewResolversMap.put(VegaCommand.Category.CREATE, VrCategoryCreate.class);
		viewResolversMap.put(VegaCommand.Category.EXECUTE_CREATE, VrCategoryRedirect2GetAll.class);
		viewResolversMap.put(VegaCommand.Category.DELETE, VrCategoryDelete.class);
		viewResolversMap.put(VegaCommand.Category.EXECUTE_DELETE, VrCategoryRedirect2GetAll.class);
		viewResolversMap.put(VegaCommand.Category.EDIT, VrCategoryEdit.class);
		viewResolversMap.put(VegaCommand.Category.EXECUTE_EDIT, VrCategoryRedirect2GetAll.class);

		viewResolversMap.put(VegaCommand.Allergen.GET_ALL, VrAllergenGetAll.class);
		viewResolversMap.put(VegaCommand.Allergen.CREATE, VrAllergenCreate.class);
		viewResolversMap.put(VegaCommand.Allergen.EXECUTE_CREATE, VrAllergenRedirect2GetAll.class);
		viewResolversMap.put(VegaCommand.Allergen.DELETE, VrAllergenDelete.class);
		viewResolversMap.put(VegaCommand.Allergen.EXECUTE_DELETE, VrAllergenRedirect2GetAll.class);
		viewResolversMap.put(VegaCommand.Allergen.EDIT, VrAllergenEdit.class);
		viewResolversMap.put(VegaCommand.Allergen.EXECUTE_EDIT, VrAllergenRedirect2GetAll.class);

		viewResolversMap.put(VegaCommand.Report.GET_ALL, VrReportGetAll.class);
		viewResolversMap.put(VegaCommand.Report.CREATE, VrReportCreate.class);
		viewResolversMap.put(VegaCommand.Report.EXECUTE_CREATE, VrReportRedirect2GetAll.class);
		viewResolversMap.put(VegaCommand.Report.VIEW_DETAILS, VrReportRedirect2GetAll.class);
		viewResolversMap.put(VegaCommand.Report.DELETE, VrReportDelete.class);
		viewResolversMap.put(VegaCommand.Report.EXECUTE_DELETE, VrReportRedirect2GetAll.class);
	}



	protected VegaViewResolverFactory(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	public ViewResolver createViewResolver() {
		if (responseContext.getStatusCode() == 200) {
			Class<? extends ViewResolver> viewResolverClass = viewResolversMap.get(requestContext.getCommandName());
			try {
				final Constructor<? extends ViewResolver> constructor = viewResolverClass.getConstructor(RequestContext.class,
						ResponseContext.class);
				return constructor.newInstance(requestContext, responseContext);
			} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return null; // TODO incompleto
			}
		} else {
			return new VrShowError(requestContext, responseContext);
		}
	}

}
