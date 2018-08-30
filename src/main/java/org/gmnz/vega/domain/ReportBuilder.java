package org.gmnz.vega.domain;


import java.util.Date;


public class ReportBuilder {

	/*
	 * non devono essere nulli
	 * 
	 * - id [mah, discutibile] - nome soggetto - data creazione - owner
	 */

	// private String id;

	private String subjectName;

	private Date createdOn;

	private String owner;



	private ReportBuilder() {
		// this.id = null;
		this.subjectName = null;
		this.createdOn = null;
		this.owner = null;
	}



	public static ReportBuilder getBuilder() {
		return new ReportBuilder();
	}



	public ReportBuilder subjectName(String subjectName) {
		this.subjectName = subjectName;
		return this;
	}



	public ReportBuilder createdOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}



	public ReportBuilder owner(String owner) {
		this.owner = owner;
		return this;
	}



	public Report build() throws ReportBuildException {
		checkIfBuildable();
		return new Report(subjectName, createdOn, owner);
	}



	private void checkIfBuildable() throws ReportBuildException {
		if (subjectName == null || createdOn == null || owner == null) {
			throw new ReportBuildException("not enough properties specified for report construction");
		}
	}

}
