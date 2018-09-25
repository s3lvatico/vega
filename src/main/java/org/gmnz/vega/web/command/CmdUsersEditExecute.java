package org.gmnz.vega.web.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 25/09/2018.
 */
class CmdUsersEditExecute extends AbstractVegaCommand {

	Vega vega;
	User targetUser;
	String userFullName;
	String newPassword;
	String newPasswordConf;
	Map<String, Boolean> selectedRoles;



	public CmdUsersEditExecute(RequestContext requestContext) {
		super(requestContext);
		selectedRoles = new HashMap<>();
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.EXECUTE_EDIT;
	}



	@SuppressWarnings("unchecked")
	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		targetUser = (User) requestContext.getSessionAttribute("user");
		userFullName = requestContext.getParameter("userFullName");
		newPassword = VegaUtil.normalizeString(requestContext.getParameter("newPassword"));
		newPasswordConf = VegaUtil.normalizeString(requestContext.getParameter("newPasswordConf"));
		List<String> roles = (List<String>) requestContext.getSessionAttribute("roles");
		for (String role : roles) {
			boolean roleIsSelected = !VegaUtil.stringNullOrEmpty(requestContext.getParameter(role));
			selectedRoles.put(role, roleIsSelected);
		}
	}



	@Override
	protected void process() throws Exception {
		if (VegaUtil.stringNullOrEmpty(userFullName)) {
			throw new Exception("invalid input for the user full name");
		}
		validatePasswordChange();
		validateSelectedRoles();

		// TODO passati questi test puoi aggiornare l'utente chiamando il service layer
	}



	private void validatePasswordChange() throws Exception {
		if (!(VegaUtil.stringNullOrEmpty(newPassword) || newPasswordConf.equals(newPassword))) {
			throw new Exception("password confirmation check failed");
		}
	}



	private void validateSelectedRoles() throws Exception {
		boolean result = false;
		for (Boolean roleIsSelected : selectedRoles.values()) {
			result |= roleIsSelected;
		}
		if (!result) {
			throw new Exception("user must have at least one role");
		}
	}

}
