package org.gmnz.vega.integration;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;


/**
 * creato da simone in data 30/12/2017.
 */
public class VgReportDataEntityPK implements Serializable {
	private String idReport;
	private String idAllergene;



	@Column(name = "id_report", nullable = false, length = 36)
	@Id
	public String getIdReport() {
		return idReport;
	}



	public void setIdReport(String idReport) {
		this.idReport = idReport;
	}



	@Column(name = "id_allergene", nullable = false, length = 36)
	@Id
	public String getIdAllergene() {
		return idAllergene;
	}



	public void setIdAllergene(String idAllergene) {
		this.idAllergene = idAllergene;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VgReportDataEntityPK that = (VgReportDataEntityPK) o;
		return Objects.equals(idReport, that.idReport) &&
				Objects.equals(idAllergene, that.idAllergene);
	}



	@Override
	public int hashCode() {

		return Objects.hash(idReport, idAllergene);
	}
}
