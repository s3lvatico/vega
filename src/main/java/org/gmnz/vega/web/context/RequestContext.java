package org.gmnz.vega.web.context;


import javax.servlet.http.HttpServletRequest;


/**
 * creato da simone in data 03/09/2018.
 */
public interface RequestContext {

	String ORIGINAL_REQUEST = "original.request";
	String SERVLET_CONTEXT = "servlet.context";

	// void setRequest(HttpServletRequest request);

	String getParameter(String name);


	Object getAttribute(String name);


	Object getFromSession(String name);



	/*
	 * Dal libro sembra che questa interfaccia debba esporre un'altro metodo,
	 * "getCommandName".
	 *
	 * Questo significa che la factory che prepara il requestContext deve arrivare a
	 * conoscere anche il particolare comando richiesto dal client. Ma questo lo si
	 * può desumere dall'url richiesto, esattamente come faccio nel controller
	 * specifico di ogni sezione
	 *
	 */

	String getCommandName();

}
