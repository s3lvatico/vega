package org.gmnz.vega.domain;

import java.util.*;

public class Report {

	private String nomeSoggetto;

	private Date dataCreazione;

	private Map<String, List<Valutazione>> reportData;

	public Report(String nomeSoggetto, Date dataCreazione) {
		this.nomeSoggetto = nomeSoggetto;
		this.dataCreazione = dataCreazione;
		reportData = new LinkedHashMap<>();
	}

	public void aggiungiValutazione(String categoria, Valutazione valutazione) {
		if (reportData.get(categoria) == null) {
			reportData.put(categoria, new ArrayList<>());
		}
		reportData.get(categoria).add(valutazione);
	}

	public Set<String> getCategorie() {
		return Collections.unmodifiableSet(reportData.keySet());
	}

	public List<Valutazione> getValutazioni(String categoria) {
		List<Valutazione> valutazioneList = reportData.get(categoria);
		return valutazioneList == null ? new ArrayList<>() : valutazioneList;
	}

	@Override
	public String toString() {
		return "Report{" +
				"nomeSoggetto='" + nomeSoggetto + '\'' +
				", dataCreazione=" + dataCreazione +
				", reportData=" + reportData +
				'}';
	}
}
