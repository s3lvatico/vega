package org.gmnz.vega.ui.web;

public class RequestProcessingResult {

	int statusCode;
	String viewName;
	String errorMessage;



	public RequestProcessingResult(int statusCode, String viewName, String errorMessage) {
		this.statusCode = statusCode;
		this.viewName = viewName;
		this.errorMessage = errorMessage;
	}



	public RequestProcessingResult(int statusCode, String errorMessage) {
		this(statusCode, null, errorMessage);
	}



	public int getStatusCode() {
		return statusCode;
	}



	public String getViewName() {
		return viewName;
	}



	public String getErrorMessage() {
		return errorMessage;
	}

}
