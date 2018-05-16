package org.gmnz.vega.integration;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;


/**
 * creato da simone in data 30/12/2017.
 */
@Entity
@Table(name = "vg_report", schema = "sandbox", catalog = "")
public class VgReportEntity {
	private String id;
	private Timestamp dataCreazione;
	private String nomeSoggetto;
	private Collection<VgReportDataEntity> vgReportDataById;



	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Basic
	@Column(name = "data_creazione", nullable = false)
	public Timestamp getDataCreazione() {
		return dataCreazione;
	}



	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}



	@Basic
	@Column(name = "nome_soggetto", nullable = false, length = 45)
	public String getNomeSoggetto() {
		return nomeSoggetto;
	}



	public void setNomeSoggetto(String nomeSoggetto) {
		this.nomeSoggetto = nomeSoggetto;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		VgReportEntity that = (VgReportEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(dataCreazione, that.dataCreazione) &&
				Objects.equals(nomeSoggetto, that.nomeSoggetto);
	}



	@Override
	public int hashCode() {

		return Objects.hash(id, dataCreazione, nomeSoggetto);
	}



	@OneToMany(mappedBy = "vgReportByIdReport")
	public Collection<VgReportDataEntity> getVgReportDataById() {
		return vgReportDataById;
	}



	public void setVgReportDataById(Collection<VgReportDataEntity> vgReportDataById) {
		this.vgReportDataById = vgReportDataById;
	}
}
