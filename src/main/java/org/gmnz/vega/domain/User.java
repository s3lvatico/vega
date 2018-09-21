package org.gmnz.vega.domain;


import java.util.List;


/**
 * creato da simone in data 20/09/2018.
 */
public class User {

	private String userId;
	private String fullName;
	private List<String> roles;



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public List<String> getRoles() {
		return roles;
	}



	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
