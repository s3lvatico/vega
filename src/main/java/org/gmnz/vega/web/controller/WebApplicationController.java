package org.gmnz.vega.web.controller;


import org.gmnz.vega.web.context.RequestContext;
import org.gmnz.vega.web.context.ResponseContext;


class WebApplicationController implements ApplicationController {

	@Override
	public ResponseContext handleRequest(RequestContext requestContext) {
		// TODO Auto-generated method stub
		/*
		chiama la command factory
		crea il comando sulla base del nome (sta nel requestContext)
		inietta nel comando i parametri
		esegui il comando
		 */
		return null;
	}



	@Override
	public void handleResponse(RequestContext requestContext, ResponseContext responseContext) {
		// TODO Auto-generated method stub
		/*
		da richiesta e risposta determina una vista
		 */
	}

}
