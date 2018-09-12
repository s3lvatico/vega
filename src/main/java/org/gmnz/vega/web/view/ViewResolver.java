package org.gmnz.vega.web.view;


import java.io.IOException;

import javax.servlet.ServletException;

import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


/**
 * creato da simone in data 08/09/2018.
 */
public interface ViewResolver {

	void resolveToView(RequestContext requestContext, ResponseContext responseContext) throws ServletException, IOException;

}
