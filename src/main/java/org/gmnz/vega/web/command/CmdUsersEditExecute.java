package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.User;
import org.gmnz.vega.web.context.RequestContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
		newPassword = requestContext.getParameter("newPassword");
		newPasswordConf = requestContext.getParameter("newPasswordConf");
		List<String> roles = (List<String>) requestContext.getSessionAttribute("roles");
		for (String role : roles) {
			boolean roleIsSelected = !VegaUtil.stringNullOrEmpty(requestContext.getParameter(role));
			selectedRoles.put(role, roleIsSelected);
		}
	}



	@Override
	protected void process() throws Exception {
		if (userFullName.isEmpty()) {
			throw new Exception("invalid input for the user full name");
		}
		boolean passwordChangeRequested = validatePasswordChange();
		validateSelectedRoles();



		// TODO passati questi test puoi aggiornare l'utente chiamando il service layer
	}



	private boolean validatePasswordChange() throws Exception {
		if (!newPassword.isEmpty()) {
			if (newPasswordConf.equals(newPassword)) {
				return true;
			}
			else {
				throw new Exception("password confirmation check failed");
			}
		}
		else {
			return false;
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
