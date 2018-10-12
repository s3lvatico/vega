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
		commandsMap.put(VegaCommand.Category.GET_ALL, CmdCategoryGetAll.class);
		commandsMap.put(VegaCommand.Category.CREATE, CmdCategoryCreate.class);
		commandsMap.put(VegaCommand.Category.EXECUTE_CREATE, CmdCategoryCreateExec.class);
		commandsMap.put(VegaCommand.Category.DELETE, CmdCategoryDelete.class);
		commandsMap.put(VegaCommand.Category.EXECUTE_DELETE, CmdCategoryDeleteExec.class);
		commandsMap.put(VegaCommand.Category.EDIT, CmdCategoryEdit.class);
		commandsMap.put(VegaCommand.Category.EXECUTE_EDIT, CmdCategoryEditExecute.class);

		commandsMap.put(VegaCommand.Allergen.GET_ALL, CmdAllergenGetAll.class);
		commandsMap.put(VegaCommand.Allergen.CREATE, CmdAllergenCreate.class);
		commandsMap.put(VegaCommand.Allergen.EXECUTE_CREATE, CmdAllergenCreateExec.class);
		commandsMap.put(VegaCommand.Allergen.DELETE, CmdAllergenDelete.class);
		commandsMap.put(VegaCommand.Allergen.EXECUTE_DELETE, CmdAllergenDeleteExec.class);
		commandsMap.put(VegaCommand.Allergen.EDIT, CmdAllergenEdit.class);
		commandsMap.put(VegaCommand.Allergen.EXECUTE_EDIT, CmdAllergenEditExecute.class);

		commandsMap.put(VegaCommand.Report.GET_ALL, CmdReportGetAll.class);
		commandsMap.put(VegaCommand.Report.CREATE, CmdReportCreate.class);
		commandsMap.put(VegaCommand.Report.EXECUTE_CREATE, CmdReportCreateExec.class);
		commandsMap.put(VegaCommand.Report.VIEW_DETAILS, CmdReportViewDetails.class);
		commandsMap.put(VegaCommand.Report.DELETE, CmdReportDelete.class);
		commandsMap.put(VegaCommand.Report.EXECUTE_DELETE, CmdReportDeleteExec.class);

		commandsMap.put(VegaCommand.User.GET_ALL, CmdUserGetAll.class);
		commandsMap.put(VegaCommand.User.EDIT, CmdUsersEdit.class);
		commandsMap.put(VegaCommand.User.EXECUTE_EDIT, CmdUsersEditExecute.class);
		commandsMap.put(VegaCommand.User.CREATE, CmdUsersCreate.class);
		commandsMap.put(VegaCommand.User.EXECUTE_CREATE, CmdUsersCreateExecute.class);
		commandsMap.put(VegaCommand.User.DELETE, CmdUsersDelete.class);
	}


	@Override
	public Command createCommand(RequestContext requestContext) {

		String commandName = requestContext.getCommandName();
		// System.out.format("[%s.createCommand()] commandName : %s%n", getClass().getName(), commandName);
		Class<? extends AbstractVegaCommand> commandClass = commandsMap.get(requestContext.getCommandName());
		try {
			final Constructor<? extends AbstractVegaCommand> commandClassConstructor = commandClass
					.getConstructor(RequestContext.class);
			return commandClassConstructor.newInstance(requestContext);
		}
		catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			String errorMessage = String.format("Error while creating command [%s]", commandName);
			return AbstractVegaCommand.ERROR(requestContext, "COMMAND_CONSTRUCTION_ERROR", 500, errorMessage, e);
		}
		catch (NullPointerException npe) {
			npe.printStackTrace();
			String errorMessage = String.format("Unknown command specified [%s]", commandName);
			return AbstractVegaCommand.ERROR(requestContext, "UNKNOWN_COMMAND", 400, errorMessage, npe);
		}
		catch (Exception e) {
			e.printStackTrace();
			String errorMessage = String.format("Error in building command [%s]", commandName);
			return AbstractVegaCommand.ERROR(requestContext, "COMMAND_CREATION_ERROR", 500, errorMessage, e);
		}
	}

}
