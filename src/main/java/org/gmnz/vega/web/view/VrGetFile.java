package org.gmnz.vega.web.view;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.web.command.VegaCommand;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 08/09/2018.
 */
class VrGetFile implements ViewResolver {

	@Override
	public void resolveToView(RequestContext requestContext, ResponseContext responseContext)
			throws ServletException, IOException {
		HttpServletRequest originalRequest = (HttpServletRequest) responseContext
				.getAttribute(ResponseContext.ORIGINAL_REQUEST);
		String targetFile = requestContext.getParameter(VegaCommand.TARGET_FILE);
		RequestDispatcher requestDispatcher = originalRequest.getRequestDispatcher(targetFile);
		HttpServletResponse httpServletResponse = (HttpServletResponse) responseContext
				.getAttribute(ResponseContext.ORIGINAL_RESPONSE);
		requestDispatcher.forward(originalRequest, httpServletResponse);
	}
}
