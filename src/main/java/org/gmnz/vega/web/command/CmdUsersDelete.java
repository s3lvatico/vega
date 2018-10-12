package org.gmnz.vega.web.command;


import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.service.UserService;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdUsersDelete extends AbstractVegaCommand {

	private String userId;
	private UserService userService;



	public CmdUsersDelete(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.DELETE;
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
			String errorMessage = String.format("no user found with id [%s]", userId);
			throw new Exception(errorMessage);
		}
		model.setAttribute("targetUser", targetUser);
		model.setAttribute("action", setCommandName());
	}

}
