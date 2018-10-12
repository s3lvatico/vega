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
class CmdUsersCreateExecute extends AbstractVegaCommand {

	private String userId;
	private String userFullName;
	private String password;
	private String passwordConfirmation;
	private ArrayList<String> roles;

	private UserService userService;



	public CmdUsersCreateExecute(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.EXECUTE_CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		userId = requestContext.getParameter("userId");
		userFullName = requestContext.getParameter("userFullName");
		password = requestContext.getParameter("password");
		passwordConfirmation = requestContext.getParameter("passwordConfirmation");
		roles = new ArrayList<>();
		for (String paramName : requestContext.getParameterNames()) {
			if (paramName.startsWith("role-")) {				
				roles.add(paramName.substring(5));
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
			errorMessages.add("a password must be specified");
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
			vr.errors = sbErrors.toString();
		}
		return vr;
	}



	@Override
	protected void process() throws Exception {
		ValidationResult vr = validateInput();
		if (vr.ok) {
			userService.createUser(userId, userFullName, password, new HashSet<>(roles));
		} else {
			throw new Exception("Input validation failed : " + vr.errors);
		}
	}

}
