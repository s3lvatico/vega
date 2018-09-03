package org.gmnz.vega.web;


import javax.servlet.http.HttpServletRequest;


/**
 * creato da simone in data 03/09/2018.
 */
public abstract class RequestContextFactory {

	public static RequestContextFactory getFactory() {
		return new HttpRequestContextFactory();
	}

	abstract public RequestContext createContext(HttpServletRequest req);
}
