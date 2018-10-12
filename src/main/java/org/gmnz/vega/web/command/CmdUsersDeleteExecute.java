package org.gmnz.vega.web.command;


import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.service.UserService;
import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 12/10/2018.
 */
class CmdUsersDeleteExecute extends AbstractVegaCommand {

	private String userId;
	private UserService userService;



	public CmdUsersDeleteExecute(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.EXECUTE_DELETE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		userService = new VegaImpl().getUserService();
		userId = requestContext.getParameter("userId");
	}



	@Override
	protected void process() throws Exception {
		User targetUser = userService.getUserById(userId);

		if (targetUser == null) {
			String errorMessage = String.format("WARNING: no user found with id [%s]", userId);
			System.err.println(errorMessage);
			return;
		}

		String currentUserId = (String) model.getAttribute(ContextProperty.CURRENT_USER);
		if (userId.equalsIgnoreCase(currentUserId)) {
			throw new Exception("you cannot delete your own user");
		}

		userService.removeUser(userId);
	}

}
