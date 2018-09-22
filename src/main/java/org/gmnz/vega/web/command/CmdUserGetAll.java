package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.web.context.RequestContext;

import java.util.List;


class CmdUserGetAll extends AbstractVegaCommand {

	private Vega vega;



	public CmdUserGetAll(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.GET_ALL;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
	}



	@Override
	protected void process() throws Exception {
		List<User> users = vega.getUserService().getAllUsers();
		model.setAttribute("users", users);
	}

}
