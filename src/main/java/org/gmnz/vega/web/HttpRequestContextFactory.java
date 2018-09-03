package org.gmnz.vega.web;


import javax.servlet.http.HttpServletRequest;


/**
 * creato da simone in data 03/09/2018.
 */
class HttpRequestContextFactory extends RequestContextFactory {

	HttpRequestContextFactory() {}



	@Override
	public RequestContext createContext(HttpServletRequest req) {
		return new HttpRequestContext(req);
	}

}
