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

		String GET_ALL = "category.getAll";
		String CREATE = "category.create";
		String DELETE = "category.delete";
		String EDIT = "category.edit";

		String EXECUTE = "category.do.";
		String EXECUTE_CREATE = EXECUTE + CREATE;
		String EXECUTE_DELETE = EXECUTE + DELETE;
		String EXECUTE_EDIT = EXECUTE + EDIT;

	}






	interface Allergen {

		String GET_ALL = "allergen.getAll";
		String CREATE = "allergen.create";
		String DELETE = "allergen.delete";
		String EDIT = "allergen.edit";

		String EXECUTE = "allergen.do.";
		String EXECUTE_CREATE = EXECUTE + CREATE;
		String EXECUTE_DELETE = EXECUTE + DELETE;
		String EXECUTE_EDIT = EXECUTE + EDIT;

	}






	interface Report {

		String GET_ALL = "report.getAll";
		String CREATE = "report.create";
		String DELETE = "report.delete";
		String VIEW_DETAILS = "report.viewDetails";

		String EXECUTE = "report.do.";
		String EXECUTE_CREATE = EXECUTE + CREATE;
		String EXECUTE_DELETE = EXECUTE + DELETE;

	}






	interface User {

		String GET_ALL = "users.getAll";
		String CREATE = "users.create";
		String DELETE = "users.delete";
		String EDIT = "users.edit";

		String EXECUTE = "users.do.";
		String EXECUTE_CREATE = EXECUTE + CREATE;
		String EXECUTE_DELETE = EXECUTE + DELETE;
		String EXECUTE_EDIT = EXECUTE + EDIT;

	}

}
