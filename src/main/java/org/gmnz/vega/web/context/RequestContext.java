package org.gmnz.vega.web.context;

/**
 * creato da simone in data 03/09/2018.
 */
public interface RequestContext {

	String getParameter(String name);



	Object getAttribute(String name);



	Object getFromSession(String name);

	/*
	 * Dal libro sembra che questa interfaccia debba esporre un'altro metodo,
	 * "getCommandName".
	 * 
	 * Questo significa che la factory che prepara il requestContext deve arrivare a
	 * conoscere anche il particolare comando richiesto dal client.
	 * 
	 */

}
