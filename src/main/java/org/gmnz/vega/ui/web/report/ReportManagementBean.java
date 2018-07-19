package org.gmnz.vega.ui.web.report;

public class ReportManagementBean {

	private String operationLabel;

	private String viewName;

	private String action;



	public String getOperationLabel() {
		return operationLabel;
	}



	public void setOperationLabel(String operationLabel) {
		if (operationLabel != null && !operationLabel.isEmpty()) {
			this.operationLabel = operationLabel;
		}
	}



	public String getViewName() {
		return viewName;
	}



	public void setViewName(String viewName) {
		this.viewName = viewName;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}

}
