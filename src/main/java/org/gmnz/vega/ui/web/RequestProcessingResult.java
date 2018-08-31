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



	public static RequestProcessingResult OK(String viewName) {
		return new RequestProcessingResult(200, viewName, null);
	}



	public static RequestProcessingResult BAD_REQUEST(String message) {
		return new RequestProcessingResult(400, message);
	}



	public static RequestProcessingResult NOT_FOUND(String message) {
		return new RequestProcessingResult(404, message);
	}



	public static RequestProcessingResult INTERNAL_SERVER_ERROR(String message) {
		return new RequestProcessingResult(500, message);
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
