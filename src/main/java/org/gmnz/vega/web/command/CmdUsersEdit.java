package org.gmnz.vega.web.command;


import java.util.ArrayList;
import java.util.List;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.service.UserService;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.view.UserRolesBean;


/**
 * creato da simone in data 16/09/2018.
 */
class CmdUsersEdit extends AbstractVegaCommand {

	private Vega vega;
	private String userId;



	public CmdUsersEdit(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.EDIT;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		userId = requestContext.getParameter("userId");
	}



	@Override
	protected void process() throws Exception {
		UserService userService = vega.getUserService();
		User user = userService.getUserById(userId);
		List<String> roles = userService.getAllRoles();

		List<UserRolesBean> userRoles = new ArrayList<>();

		for (String role : roles) {
			userRoles.add(new UserRolesBean(role, user.getRoles().contains(role)));
		}
		model.setSessionAttribute("user", user);
		model.setSessionAttribute("roles", roles);
		model.setAttribute("userRoles", userRoles);
	}

}
