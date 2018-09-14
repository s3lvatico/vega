package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;

import javax.servlet.ServletException;
import java.io.IOException;


public class VrCategoryGetAll extends AbstractViewResolver {

	public VrCategoryGetAll(RequestContext requestContext, ResponseContext responseContext) {

		super(requestContext, responseContext);
		targetViewName = "categories";

	}




	@Override
	public void resolveToView(RequestContext requestContext, ResponseContext responseContext)
			throws ServletException, IOException {

		String viewName = responseContext.getStatusCode() == 200 ? targetViewName : "showError";
		String target = String.format(FMT_JSP_ENV, viewName);
		forward(target);

	}

}
