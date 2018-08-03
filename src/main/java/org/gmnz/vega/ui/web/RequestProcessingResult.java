package org.gmnz.vega.ui.web;

public class RequestProcessingResult {

	int responseCode;
	String viewName;



	public RequestProcessingResult(int responseCode, String viewName) {
		this.responseCode = responseCode;
		this.viewName = viewName;
	}



	public RequestProcessingResult(int responseCode) {
		this(responseCode, null);
	}



	public int getResponseCode() {
		return responseCode;
	}



	public String getViewName() {
		return viewName;
	}

}
