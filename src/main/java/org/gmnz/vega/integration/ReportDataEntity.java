package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.Objects;


@Entity(name = "ReportData")
@Table(name = "vg_report_data")
@IdClass(ReportDataEntityPK.class)
public class ReportDataEntity {


	@Id
	@Column(name = "id_report", nullable = false, length = 36)
	private String idReport;

	@Id
	@Column(name = "id_allergene", nullable = false, length = 36)
	private String idAllergene;

	@Column(name = "tox", nullable = false, precision = 2)
	private double tox;

	@ManyToOne
	@JoinColumn(name = "id_report", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private ReportEntity vgReportByIdReport;

	@ManyToOne
	@JoinColumn(name = "id_allergene", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private AllergeneEntity vgAllergeneByIdAllergene;



	public String getIdReport() {
		return idReport;
	}



	public void setIdReport(String idReport) {
		this.idReport = idReport;
	}



	public String getIdAllergene() {
		return idAllergene;
	}



	public void setIdAllergene(String idAllergene) {
		this.idAllergene = idAllergene;
	}



	public double getTox() {
		return tox;
	}



	public void setTox(double tox) {
		this.tox = tox;
	}



	public ReportEntity getVgReportByIdReport() {
		return vgReportByIdReport;
	}



	public void setVgReportByIdReport(ReportEntity vgReportByIdReport) {
		this.vgReportByIdReport = vgReportByIdReport;
	}



	public AllergeneEntity getVgAllergeneByIdAllergene() {
		return vgAllergeneByIdAllergene;
	}



	public void setVgAllergeneByIdAllergene(AllergeneEntity vgAllergeneByIdAllergene) {
		this.vgAllergeneByIdAllergene = vgAllergeneByIdAllergene;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ReportDataEntity
				that = (ReportDataEntity) o;
		return Objects.equals(idReport, that.idReport) &&
				Objects.equals(idAllergene, that.idAllergene) &&
				Objects.equals(tox, that.tox);
	}



	@Override
	public int hashCode() {

		return Objects.hash(idReport, idAllergene, tox);
	}


}
