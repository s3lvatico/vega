package org.gmnz.vega.integration;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * creato da simone in data 30/12/2017.
 */
@Entity
@Table(name = "vg_report_data", schema = "sandbox", catalog = "")
@IdClass(VgReportDataEntityPK.class)
public class VgReportDataEntity {
	private String idReport;
	private String idAllergene;
	private BigDecimal tox;
	private VgReportEntity vgReportByIdReport;
	private VgAllergeneEntity vgAllergeneByIdAllergene;



	@Id
	@Column(name = "id_report", nullable = false, length = 36)
	public String getIdReport() {
		return idReport;
	}



	public void setIdReport(String idReport) {
		this.idReport = idReport;
	}



	@Id
	@Column(name = "id_allergene", nullable = false, length = 36)
	public String getIdAllergene() {
		return idAllergene;
	}



	public void setIdAllergene(String idAllergene) {
		this.idAllergene = idAllergene;
	}



	@Basic
	@Column(name = "tox", nullable = false, precision = 2)
	public BigDecimal getTox() {
		return tox;
	}



	public void setTox(BigDecimal tox) {
		this.tox = tox;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VgReportDataEntity that = (VgReportDataEntity) o;
		return Objects.equals(idReport, that.idReport) &&
				Objects.equals(idAllergene, that.idAllergene) &&
				Objects.equals(tox, that.tox);
	}



	@Override
	public int hashCode() {

		return Objects.hash(idReport, idAllergene, tox);
	}



	@ManyToOne
	@JoinColumn(name = "id_report", referencedColumnName = "id", nullable = false)
	public VgReportEntity getVgReportByIdReport() {
		return vgReportByIdReport;
	}



	public void setVgReportByIdReport(VgReportEntity vgReportByIdReport) {
		this.vgReportByIdReport = vgReportByIdReport;
	}



	@ManyToOne
	@JoinColumn(name = "id_allergene", referencedColumnName = "id", nullable = false)
	public VgAllergeneEntity getVgAllergeneByIdAllergene() {
		return vgAllergeneByIdAllergene;
	}



	public void setVgAllergeneByIdAllergene(VgAllergeneEntity vgAllergeneByIdAllergene) {
		this.vgAllergeneByIdAllergene = vgAllergeneByIdAllergene;
	}
}
