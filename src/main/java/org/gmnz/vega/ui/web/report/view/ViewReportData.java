package org.gmnz.vega.ui.web.report.view;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ViewReportData {

	private String subjectName;
	private String owner;
	private Date creationDate;
	private List<ViewReportCategory> categories;



	public ViewReportData() {
		categories = new ArrayList<>();
	}



	public String getSubjectName() {
		return subjectName;
	}



	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}



	public String getOwner() {
		return owner;
	}



	public void setOwner(String owner) {
		this.owner = owner;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public List<ViewReportCategory> getCategories() {
		return categories;
	}



	public void setCategories(List<ViewReportCategory> categories) {
		this.categories = categories;
	}
}
