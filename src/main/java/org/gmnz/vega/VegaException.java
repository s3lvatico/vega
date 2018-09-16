package org.gmnz.vega;


public class VegaException extends Exception {

	private static final long serialVersionUID = 5203809160051535474L;



	public VegaException(String message) {
		super(message);
	}



	public VegaException(String message, Throwable cause) {
		super(message, cause);
	}



	public VegaException(Throwable cause) {
		super(cause);
	}

}
