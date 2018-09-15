package org.gmnz.vega.ui.web.category;


import org.gmnz.vega.domain.Category;


public class CategoryManagementBean {

	private String operationLabel;

	@Deprecated
	private String categoryName;

	private String viewName;

	private String action;

	private Category category;



	public CategoryManagementBean() {
		categoryName = "";
		category = new Category("");
	}



	public String getOperationLabel() {
		return operationLabel;
	}



	public void setOperationLabel(String operationLabel) {
		if (operationLabel != null && !operationLabel.isEmpty()) {
			this.operationLabel = operationLabel;
		}
	}



	@Deprecated
	public String getCategoryName() {
		return categoryName;
	}



	@Deprecated
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



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}

}
