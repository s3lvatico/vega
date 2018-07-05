package org.gmnz.vega.ui;


public class CategoryManagementBean {

	private String operationLabel;

	private String categoryName;

	private String viewName;


	private String command;



	public CategoryManagementBean() {
		categoryName = "";
	}



	public String getOperationLabel() {
		return operationLabel;
	}



	public void setOperationLabel(String operationLabel) {
		if (operationLabel != null && !operationLabel.isEmpty()) {
			this.operationLabel = operationLabel;
		}
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		if (categoryName != null && !categoryName.isEmpty()) {
			this.categoryName = categoryName;
		}
	}



	public String getViewName() {
		return viewName;
	}



	public void setViewName(String viewName) {
		this.viewName = viewName;
	}



	public String getCommand() {
		return command;
	}



	public void setCommand(String command) {
		this.command = command;
	}

}
