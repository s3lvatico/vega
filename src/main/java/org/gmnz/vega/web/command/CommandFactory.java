package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 05/09/2018.
 */
public abstract class CommandFactory {

	public static CommandFactory getFactory() {
		return new VegaCommandFactory();
	}



	abstract public Command createCommand(RequestContext requestContext);

}
