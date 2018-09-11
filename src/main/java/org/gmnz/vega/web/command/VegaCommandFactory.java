package org.gmnz.vega.web.command;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 05/09/2018.
 */
class VegaCommandFactory extends CommandFactory {

	private static Map<String, Class<? extends AbstractVegaCommand>> commandsMap = new HashMap<>();

	static {
		commandsMap.put(VegaCommand.GET_FILE, CommandGetFile.class);
	}



	@Override
	public Command createCommand(RequestContext requestContext) {
		String commandName = requestContext.getCommandName();
		System.out.format("[%s.createCommand()] commandName : %s%n", getClass().getName(), commandName);
		Class<? extends AbstractVegaCommand> commandClass = commandsMap.get(requestContext.getCommandName());
		try {
			final Constructor<? extends AbstractVegaCommand> commandClassConstructor = commandClass
					.getConstructor(RequestContext.class);
			return commandClassConstructor.newInstance(requestContext);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			// perlopiù questa è una NPE, e salta fuori quando non c'è una mappatura tra nome e comando
			requestContext.seta
			CommandShowError cse = new CommandShowError(requestContext);
		}
		return null;
	}

}