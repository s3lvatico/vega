package org.gmnz.a2.domain;

import java.util.Date;
import java.util.List;

public class Report extends AbstractListManagedPropertyHolder<Valutazione> {

	private String nomeSoggetto;

	private Date dataCreazione;


	public Report(String nomeSoggetto, Date dataCreazione) {
		this.nomeSoggetto = nomeSoggetto;
		this.dataCreazione = dataCreazione;
	}

	public List<Valutazione> getValutazioni() {
		return getListProperty();
	}


}
