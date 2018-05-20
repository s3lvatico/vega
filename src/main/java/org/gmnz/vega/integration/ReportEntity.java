package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.Date;


@Entity(name = "Report")
@Table(name = "report")
public class ReportEntity {

	@Id
	@Column(name = "uuid")
	private String id;

	@Column(name = "date_creation")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@Column(name = "subject_name", length = 45)
	private String subjectName;



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Date getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}



	public String getSubjectName() {
		return subjectName;
	}



	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
