package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.ui.web.category.CategoryManagementBean;
import org.gmnz.vega.web.context.RequestContext;


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
		User user = vega.getUserService().getUserById(userId);

		model.setAttribute("categoryBean", cmb);
	}

}
