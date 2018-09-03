package org.gmnz.vega.web.controller;


import javax.servlet.http.HttpServletRequest;


class WebApplicationControllerFactory extends ApplicationControllerFactory {

	@Override
	public ApplicationController createApplicationController(HttpServletRequest request) {
		return new WebApplicationController();
	}

}
