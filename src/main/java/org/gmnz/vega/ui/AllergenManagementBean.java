package org.gmnz.vega.ui;

public class AllergenManagementBean {

	private String operationLabel;

	private String allergenName;

	private String viewName;

	private String action;



	public AllergenManagementBean() {
		allergenName = "";
	}



	public String getOperationLabel() {
		return operationLabel;
	}



	public void setOperationLabel(String operationLabel) {
		if (operationLabel != null && !operationLabel.isEmpty()) {
			this.operationLabel = operationLabel;
		}
	}



	public String getAllergenName() {
		return allergenName;
	}



	public void setAllergenName(String allergenName) {
		if (allergenName != null && !allergenName.isEmpty()) {
			this.allergenName = allergenName;
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
