package org.gmnz.vega.web.view;


import java.io.IOException;

import javax.servlet.ServletException;

import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


public class VrCategoryGetAll extends AbstractViewResolver {

	public VrCategoryGetAll(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
		VIEW_NAME = "categories";
	}



	@Override
	public void resolveToView(RequestContext requestContext, ResponseContext responseContext)
			throws ServletException, IOException {
		String target = String.format(FMT_JSP_ENV, VIEW_NAME);
		forward(target);
	}

}
