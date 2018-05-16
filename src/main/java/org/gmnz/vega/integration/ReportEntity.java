package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.Date;


@Entity(name = "Report")
@Table(name = "vg_report")
public class ReportEntity {

	@Id
	private String id;

	@Column(name = "data_creazione")
	@Temporal(TemporalType.DATE)
	private Date dataCreazione;

	@Column(name = "nome_soggetto", length = 45)
	private String nomeSoggetto;



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Date getDataCreazione() {
		return dataCreazione;
	}



	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}



	public String getNomeSoggetto() {
		return nomeSoggetto;
	}



	public void setNomeSoggetto(String nomeSoggetto) {
		this.nomeSoggetto = nomeSoggetto;
	}
}
