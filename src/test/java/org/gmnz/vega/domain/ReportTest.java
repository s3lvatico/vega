package org.gmnz.vega.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ReportTest {

	private Categoria cereali;
	private Allergene avena;
	private Allergene farina;
	private Allergene orzo;
	private Allergene patate;
	private Categoria condimenti;
	private Allergene lievitoDiBirra;
	private Allergene olioDiOliva;
	private Allergene strutto;

	@Before public void buildData() {
		cereali = new Categoria("Cereali");

		avena = new Allergene("Avena");
		farina = new Allergene("Farina");
		orzo = new Allergene("Orzo");
		patate = new Allergene("Patate");

		cereali.add(avena); cereali.add(farina); cereali.add(orzo); cereali.add(patate);


		condimenti = new Categoria("Condimenti");

		lievitoDiBirra = new Allergene("Lievito di birra");
		olioDiOliva = new Allergene("Olio di oliva");
		strutto = new Allergene("Strutto");

		condimenti.add(lievitoDiBirra); condimenti.add(olioDiOliva); condimenti.add(strutto);
	}

	@Test public void creaReport() {
		Report report = new Report("Simone", new Date());
		System.out.println(report);


		Valutazione v = new Valutazione(avena, 0.8d);
		report.aggiungiValutazione(cereali.getNome(), v);

		v = new Valutazione(farina, 0.97d);
		report.aggiungiValutazione(cereali.getNome(), v);

		v = new Valutazione(orzo, 0.35d);
		report.aggiungiValutazione(cereali.getNome(), v);

		v = new Valutazione(patate, 0.79d);
		report.aggiungiValutazione(cereali.getNome(), v);

		System.out.println(report);

		v = new Valutazione(lievitoDiBirra, 0.93d);
		report.aggiungiValutazione(condimenti.getNome(), v);

		v = new Valutazione(olioDiOliva, 0.61d);
		report.aggiungiValutazione(condimenti.getNome(), v);

		v = new Valutazione(strutto, 0.81d);
		report.aggiungiValutazione(condimenti.getNome(), v);

		System.out.println(report);

	}
}
