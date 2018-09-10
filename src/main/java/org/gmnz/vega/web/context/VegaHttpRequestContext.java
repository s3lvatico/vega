package org.gmnz.vega.web.context;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gmnz.vega.web.command.VegaCommand;


/**
 * creato da simone in data 03/09/2018.
 */
@SuppressWarnings("Duplicates")
class VegaHttpRequestContext implements RequestContext {

	private static final String CMD_NAME_PREFIX = "/app/";

	private final Map<String, String> parameters;
	private final Map<String, Object> attributes;
	private final Map<String, Object> sessionStorage;

	private String commandName;



	VegaHttpRequestContext(HttpServletRequest request) {
		parameters = new HashMap<>();
		attributes = new HashMap<>();
		sessionStorage = new HashMap<>();
		init(request);
	}



	private void init(HttpServletRequest request) {

		fillParametersMap(request);

		fillAttributesMap(request);

		fillSessionMap(request.getSession());

		String requestUri = request.getRequestURI();
		String cmdBlock = requestUri.substring(requestUri.indexOf(CMD_NAME_PREFIX) + CMD_NAME_PREFIX.length());

		boolean isFile = checkForFile(cmdBlock);
		if (isFile) {
			commandName = VegaCommand.GET_FILE;
			parameters.put(VegaCommand.CMD_NAME, commandName);
			parameters.put(VegaCommand.TARGET_FILE, cmdBlock);
		} else {
			// TODO determina il comando nelle altre condizioni
		}
	}



	private void fillParametersMap(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (String paramName : parameterMap.keySet()) {
			String[] paramValues = parameterMap.get(paramName);
			if (paramValues != null) {
				if (paramValues.length != 1) {
					System.err.format(
							"WARNING: parameter [%s] has more than one value - only the first value will be stored%n",
							paramName);
				}
				parameters.put(paramName, paramValues[0]);
			}
		}
	}



	private void fillAttributesMap(HttpServletRequest request) {
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = request.getAttribute(attributeName);
			if (attributeValue != null) {
				attributes.put(attributeName, attributeValue);
			}
		}
	}



	private void fillSessionMap(HttpSession session) {
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(attributeName);
			if (attributeValue != null) {
				sessionStorage.put(attributeName, attributeValue);
			}
		}
	}



	private boolean checkForFile(String requestUri) {
		return requestUri.endsWith(".jsp");
	}



	@Override
	public void setRequest(HttpServletRequest request) {
		attributes.put(RequestContext.ORIGINAL_REQUEST, request);
	}



	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}



	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}



	@Override
	public Object getFromSession(String name) {
		return sessionStorage.get(name);
	}



	@Override
	public String getCommandName() {
		return commandName;
	}

}
