package org.gmnz.vega.repository;


public class DaoException extends Exception {

	public DaoException(String message) {
		super(message);
	}



	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}



	public DaoException(Throwable cause) {
		super(cause);
	}
}
