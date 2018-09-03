package org.gmnz.vega.web.controller;


import javax.servlet.http.HttpServletRequest;


/**
 * creato da simone in data 03/09/2018.
 */
public abstract class ApplicationControllerFactory {

	public static ApplicationControllerFactory getFactory() {
		return new WebApplicationControllerFactory();
	}



	public abstract ApplicationController createApplicationController(HttpServletRequest request);

}
