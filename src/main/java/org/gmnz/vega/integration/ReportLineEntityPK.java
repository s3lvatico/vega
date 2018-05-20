package org.gmnz.vega.integration;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;


public class ReportLineEntityPK implements Serializable {

	@Column(name = "uuid", nullable = false, length = 36)
	@Id
	private String id;

	@Column(name = "id_report", nullable = false, length = 36)
	@Id
	private String idReport;



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



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ReportLineEntityPK that = (ReportLineEntityPK) o;
		return Objects.equals(idReport, that.idReport) &&
				Objects.equals(idReport, that.idReport);
	}



	@Override
	public int hashCode() {
		return Objects.hash(idReport, idReport);
	}
}
