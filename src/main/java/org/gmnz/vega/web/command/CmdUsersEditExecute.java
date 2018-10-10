package org.gmnz.vega.web.command;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.service.UserService;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 10/10/2018.
 */
class CmdUsersEditExecute extends AbstractVegaCommand {

	String userId;
	String userFullName;
	String password;
	String passwordConfirmation;
	ArrayList<String> roles;

	UserService userService;



	public CmdUsersEditExecute(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.EXECUTE_CREATE;
	}



	@SuppressWarnings("unchecked")
	@Override
	protected void initialize(RequestContext requestContext) {
		userId = requestContext.getParameter("userId");
		userFullName = requestContext.getParameter("userFullName");
		password = requestContext.getParameter("password");
		passwordConfirmation = requestContext.getParameter("passwordConfirmation");
		roles = new ArrayList<>();
		for (String paramName : requestContext.getParameterNames()) {
			if (paramName.startsWith("role-")) {
				roles.add(requestContext.getParameter(paramName));
			}
		}
		userService = new VegaImpl().getUserService();
	}



	private ValidationResult validateInput() {
		HashSet<String> errorMessages = new HashSet<>();
		if (userId.isEmpty()) {
			errorMessages.add("userId is mandatory");
		}
		if (userFullName.isEmpty()) {
			errorMessages.add("userFullName is mandatory");
		}
		if (password.isEmpty()) {
			errorMessages.add("a password muse be specified");
		} else {
			if (!passwordConfirmation.equals(password)) {
				errorMessages.add("password confirmation failed");
			}
		}
		if (roles.isEmpty()) {
			errorMessages.add("the new user must have at least one role");
		}

		return buildValidationResult(errorMessages);
	}



	private ValidationResult buildValidationResult(Set<String> errors) {
		ValidationResult vr = new ValidationResult();
		vr.ok = errors.isEmpty();
		if (!errors.isEmpty()) {
			StringBuilder sbErrors = new StringBuilder();
			int n = errors.size() - 1;
			int j = 0;
			for (String err : errors) {
				sbErrors.append(err);
				if (j < n) {
					sbErrors.append(" | ");
				}
				j++;
			}
		}
		return vr;
	}



	@Override
	protected void process() throws Exception {
		ValidationResult vr = validateInput();
		if (vr.ok) {
			// TODO crea utente
		} else {
			throw new Exception("Input validation failed : " + vr.errors);
		}
	}

}
