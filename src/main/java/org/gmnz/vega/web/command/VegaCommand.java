package org.gmnz.vega.web.command;

/**
 * creato da simone in data 08/09/2018.
 */
public interface VegaCommand {

	String CMD_NAME = "command.name";

	/**
	 * @deprecated da eliminare
	 */
	@Deprecated
	String GET_FILE = "get.file";

	/**
	 * @deprecated da eliminare
	 */
	@Deprecated
	String TARGET_FILE = "target.file";

	interface Category {

		String EXECUTE = "category.do.";
		String GET_ALL = "category.getAll";
		String CREATE = "category.create";
		String EXECUTE_CREATE = EXECUTE + CREATE;
		String CREATE_EXEC = "category.create.execute";


	}
}
