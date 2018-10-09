package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.service.UserService;
import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.view.UserRolesBean;

import java.util.ArrayList;
import java.util.List;


/**
 * creato da simone in data 8/10/2018.
 */
class CmdUsersCreate extends AbstractVegaCommand {

	private UserService userService;
	private String userId;



	public CmdUsersCreate(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) { }




	@Override
	protected void process() throws Exception {
		UserService userService = new VegaImpl().getUserService();
		List<String> roles = userService.getAllRoles();
		List<UserRolesBean> userRoles = new ArrayList<>();

		for (String role : roles) {
			userRoles.add(new UserRolesBean(role, false));
		}
		model.setSessionAttribute("userRoles", userRoles);
	}

}
