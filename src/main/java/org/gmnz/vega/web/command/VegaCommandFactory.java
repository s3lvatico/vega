package org.gmnz.vega.web.command;


import org.gmnz.vega.web.context.RequestContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


/**
 * creato da simone in data 05/09/2018.
 */
class VegaCommandFactory extends CommandFactory {

	private static Map<String, Class<? extends AbstractVegaCommand>> commandsMap = new HashMap<>();

	static {
		commandsMap.put(VegaCommand.GET_FILE, CommandGetFile.class);
	}

	/*
	nell'oggetto Command devono essere trasferiti tutti i parametri necessari ad eseguire il comando
	 */



	@Override
	public Command createCommand(RequestContext requestContext) {
		Class<? extends AbstractVegaCommand> commandClass = commandsMap.get(requestContext.getCommandName());
		try {
			final Constructor<? extends AbstractVegaCommand> commandClassConstructor = commandClass.getConstructor(RequestContext.class);
			return commandClassConstructor.newInstance(requestContext);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
