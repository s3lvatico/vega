package org.gmnz.vega.web.context;


/**
 * creato da simone in data 08/09/2018.
 */
public abstract class ResponseContextFactory {

	public static ResponseContextFactory getFactory() {
		return new HttpResponseContextFactory();
	}



	abstract public ResponseContext createResponseContext();

}
