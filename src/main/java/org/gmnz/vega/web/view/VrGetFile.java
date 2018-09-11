package org.gmnz.vega.web.view;


import org.gmnz.vega.web.command.VegaCommand;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * creato da simone in data 08/09/2018.
 *
 * @deprecated perché ho capito che il front controller gestisce richieste http
 * che riguardano comandi veri e propri, come se fosse un gestore di api
 * restful
 *
 * se devo visualizzare solo un file, o se la richiesta è indirizzata ad una
 * risorsa statica, il front controller non dovrebbe intervenire
 */
@Deprecated
class VrGetFile extends AbstractViewResolver {

	public VrGetFile(RequestContext requestContext, ResponseContext responseContext) {
		super(requestContext, responseContext);
	}



	@Override
	public void resolveToView(RequestContext requestContext, ResponseContext responseContext)
			throws ServletException, IOException {


//		HttpServletRequest httpServletRequest = (HttpServletRequest) requestContext.getAttribute(RequestContext.ORIGINAL_REQUEST);
//		HttpServletResponse httpServletResponse = (HttpServletResponse) responseContext.getAttribute(ResponseContext.ORIGINAL_RESPONSE);

		String targetFile = requestContext.getParameter(VegaCommand.TARGET_FILE);
		forward(targetFile);

//		ServletContext servletContext = (ServletContext) requestContext.getAttribute(RequestContext.SERVLET_CONTEXT);
//		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(targetFile);
//		requestDispatcher.forward(httpServletRequest, httpServletResponse);

//		RequestDispatcher requestDispatcher = originalRequest.getRequestDispatcher(targetFile);
//		requestDispatcher.forward(originalRequest, httpServletResponse);
	}

}
