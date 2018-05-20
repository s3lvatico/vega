package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.Objects;


@Entity(name = "ReportData")
@Table(name = "report_line")
@IdClass(ReportLineEntityPK.class)
public class ReportLineEntity {


	@Id
	@Column(name = "uuid", nullable = false, length = 36)
	private String id;

	@Id
	@Column(name = "id_report", nullable = false, length = 36)
	private String idReport;

	@Column(name = "toxicity", nullable = false, precision = 2)
	private double toxicity;

	@ManyToOne
	@JoinColumn(name = "id_report", referencedColumnName = "uuid", nullable = false, insertable = false, updatable = false)
	private ReportEntity report;

	@ManyToOne
	@JoinColumn(name = "allergen_id", referencedColumnName = "uuid")
	private AllergenEntity allergen;



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getIdReport() {
		return idReport;
	}



	public void setIdReport(String idReport) {
		this.idReport = idReport;
	}



	public double getToxicity() {
		return toxicity;
	}



	public void setToxicity(double toxicity) {
		this.toxicity = toxicity;
	}



	public ReportEntity getReport() {
		return report;
	}



	public void setReport(ReportEntity report) {
		this.report = report;
	}



	public AllergenEntity getAllergen() {
		return allergen;
	}



	public void setAllergen(AllergenEntity allergen) {
		this.allergen = allergen;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ReportLineEntity
				that = (ReportLineEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(idReport, that.idReport) &&
				Objects.equals(toxicity, that.toxicity);
	}



	@Override
	public int hashCode() {

		return Objects.hash(id, idReport, toxicity);
	}


}
