package org.gmnz.vega.web;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.VegaUtil;


/**
 * creato da simone in data 03/09/2018.
 */
class HttpRequestContext implements RequestContext {

	private final Map<String, Object> values;



	HttpRequestContext(HttpServletRequest request) {
		values = new HashMap<>();
	}



	private void init(HttpServletRequest request) {
		// TODO trasferisci parametri ed attributi nella mappa
	}



	@Override
	public Object getProperty(String parameterName) {
		return values.get(parameterName);
	}



	@Override
	public void setProperty(String name, Object value) {
		if (value != null) {
			String nName = VegaUtil.normalizeString(name);
			if (!VegaUtil.stringNullOrEmpty(nName)) {
				values.put(nName, value);
			}
		}
	}

}
