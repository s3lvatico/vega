package org.gmnz.vega.ui.web.allergen;


import org.gmnz.vega.domain.Allergen;


public class AllergenManagementBean {

	private String operationLabel;

	private String viewName;

	private String action;

	private Allergen allergen;



	public Allergen getAllergen() {
		return allergen;
	}



	public void setAllergen(Allergen allergen) {
		this.allergen = allergen;
	}



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
