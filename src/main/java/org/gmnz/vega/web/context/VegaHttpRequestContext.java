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
class VegaHttpRequestContext extends AbstractContextObject implements RequestContext {

	private static final String CMD_NAME_PREFIX = "/app/";

	private final Map<String, Object> sessionStorage;

	private String commandName;



	VegaHttpRequestContext(HttpServletRequest request) {
		sessionStorage = new HashMap<>();
		init(request);
	}



	private void init(HttpServletRequest request) {
		fillParametersMap(request);
		fillAttributesMap(request);
		fillSessionMap(request.getSession());

		String requestUri = request.getRequestURI();
		String cmdBlock = requestUri.substring(requestUri.indexOf(CMD_NAME_PREFIX) + CMD_NAME_PREFIX.length());

		commandName = cmdBlock.replace('/', '.');
		setParameter(VegaCommand.CMD_NAME, commandName);

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
				setParameter(paramName, paramValues[0]);
			}
		}
	}



	private void fillAttributesMap(HttpServletRequest request) {
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = request.getAttribute(attributeName);
			if (attributeValue != null) {
				setAttribute(attributeName, attributeValue);
			}
		}
		setAttribute(RequestContext.ORIGINAL_REQUEST, request);
		setAttribute(RequestContext.SERVLET_CONTEXT, request.getServletContext());
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
