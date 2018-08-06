package org.gmnz.vega.ui.web.report;


import java.util.ArrayList;
import java.util.List;


public class ViewReportCategory {

	private String name;
	private List<ViewReportToxicityAssessment> toxData;



	public ViewReportCategory() {
		toxData = new ArrayList<>();
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<ViewReportToxicityAssessment> getToxData() {
		return toxData;
	}



	public void setToxData(List<ViewReportToxicityAssessment> toxData) {
		this.toxData = toxData;
	}
}
