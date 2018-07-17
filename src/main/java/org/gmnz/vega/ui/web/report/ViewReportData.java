package org.gmnz.vega.ui.web.report;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ViewReportData {

	private String subjectName;
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
