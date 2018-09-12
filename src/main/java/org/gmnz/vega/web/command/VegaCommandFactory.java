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
		// TODO sostituisci CommandGetFile con gli altri comandi veri
		commandsMap.put(VegaCommand.GET_FILE, CommandGetFile.class);
		commandsMap.put(VegaCommand.Category.GET_ALL, CmdCategoryGetAll.class);
		
		// TODO verifica il caso di classi inesistenti!
		
		// e gli altri
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
			String errorMessage = String.format("Error while creating command [%s]", commandName);
			requestContext.setAttribute(RequestContext.STATUS_CODE, 503);
			requestContext.setParameter(RequestContext.ERROR_MESSAGE, errorMessage);
			return new CommandShowError(requestContext);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			
			// perlopiù questa è una NPE, e salta fuori quando non c'è una mappatura tra
			// nome e comando
			String errorMessage = String.format("Unknown command specified [%s]", commandName);
			requestContext.setAttribute(RequestContext.STATUS_CODE, 404);
			requestContext.setParameter(RequestContext.ERROR_MESSAGE, errorMessage);
			return new CommandShowError(requestContext);
		}
	}

}
