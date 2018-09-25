package org.gmnz.vega.web.view;

public class UserRolesBean {

	private String name;
	private boolean selected;



	public UserRolesBean() {
		this.name = "";
		this.selected = false;
	}



	public UserRolesBean(String name, boolean selected) {
		super();
		this.name = name;
		this.selected = selected;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public boolean isSelected() {
		return selected;
	}



	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
