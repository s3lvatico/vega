package org.gmnz.vega.web.context;

/**
 * creato da simone in data 03/09/2018.
 */
public interface RequestContext extends ContextObject {

	String ORIGINAL_REQUEST = "requestcontext.original.request";
	String SERVLET_CONTEXT = "requestcontext.servlet.context";

	String STATUS_CODE = "requestcontext.status.code";

	String ERROR_MESSAGE = "requestcontext.error.message";



	public Object getFromSession(String name);



	String getCommandName();

}
