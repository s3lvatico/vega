package org.gmnz.vega.web.view;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * creato da simone in data 15/09/2018.
 */
public class VrShowError extends AbstractViewResolver {

	private static final String ERROR_VIEW_NAME = "showError";



	public VrShowError(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
		targetViewName = ERROR_VIEW_NAME;
	}



	@Override
	public void resolveToView(RequestContext requestContext, ResponseContext responseContext) throws ServletException, IOException {
		String target = String.format(FMT_JSP_ENV, ERROR_VIEW_NAME);
		forward(target);
	}

}
