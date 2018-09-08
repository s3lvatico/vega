package org.gmnz.vega.web.context;


/**
 * creato da simone in data 08/09/2018.
 */
class HttpResponseContextFactory extends ResponseContextFactory {

	@Override
	public ResponseContext createResponseContext() {
		return new VegaHttpResponseContext();
	}

}
