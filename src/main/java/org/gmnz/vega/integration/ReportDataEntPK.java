package org.gmnz.vega.integration;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;


public class ReportDataEntPK implements Serializable {

	@Column(name = "id_report", nullable = false, length = 36)
	@Id
	private String idReport;

	@Column(name = "id_allergene", nullable = false, length = 36)
	@Id
	private String idAllergene;



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



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ReportDataEntPK that = (ReportDataEntPK) o;
		return Objects.equals(idReport, that.idReport) &&
				Objects.equals(idAllergene, that.idAllergene);
	}



	@Override
	public int hashCode() {
		return Objects.hash(idReport, idAllergene);
	}
}
