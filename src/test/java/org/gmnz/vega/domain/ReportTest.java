package org.gmnz.vega.domain;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class ReportTest {

	private Category cereali;
	private Allergen avena;
	private Allergen farina;
	private Allergen orzo;
	private Allergen patate;
	private Category condimenti;
	private Allergen lievitoDiBirra;
	private Allergen olioDiOliva;
	private Allergen strutto;



	@Before
	public void buildData() {
		cereali = new Category("Cereali");

		avena = new Allergen("Avena");
		farina = new Allergen("Farina");
		orzo = new Allergen("Orzo");
		patate = new Allergen("Patate");

		cereali.addAllergen(avena);
		cereali.addAllergen(farina);
		cereali.addAllergen(orzo);
		cereali.addAllergen(patate);

		condimenti = new Category("Condimenti");

		lievitoDiBirra = new Allergen("Lievito di birra");
		olioDiOliva = new Allergen("Olio di oliva");
		strutto = new Allergen("Strutto");

		condimenti.addAllergen(lievitoDiBirra);
		condimenti.addAllergen(olioDiOliva);
		condimenti.addAllergen(strutto);
	}



	@Test
	public void creaReport() {
		Report report = new Report("Simone", new Date(), "gemini");
		System.out.println(report);

		ToxicityRating v = new ToxicityRating(avena, 0.8d);
		report.addRating(v);

		v = new ToxicityRating(farina, 0.97d);
		report.addRating(v);

		v = new ToxicityRating(orzo, 0.35d);
		report.addRating(v);

		v = new ToxicityRating(patate, 0.79d);
		report.addRating(v);

		System.out.println(report);

		v = new ToxicityRating(lievitoDiBirra, 0.93d);
		report.addRating(v);

		v = new ToxicityRating(olioDiOliva, 0.61d);
		report.addRating(v);

		v = new ToxicityRating(strutto, 0.81d);
		report.addRating(v);

		System.out.println(report);
	}



	@Test
	public void reportBuilder() throws ReportBuildException {
		System.out.println("builder test");
		try {
			ReportBuilder.getBuilder().build();
		} catch (ReportBuildException e) {
			System.err.println("all nulls - exception ok");
		}
		try {
			ReportBuilder.getBuilder().owner("nazzo").build();
		} catch (ReportBuildException e) {
			System.err.println("owner , null, null - exception ok");
		}
		try {
			ReportBuilder.getBuilder().createdOn(new Date()).build();
		} catch (ReportBuildException e) {
			System.err.println("null, date, null - exception ok");
		}
		try {
			ReportBuilder.getBuilder().owner("nazzo").createdOn(new Date()).build();
		} catch (ReportBuildException e) {
			System.err.println("owner, date, null - exception ok");
		}
		try {
			ReportBuilder.getBuilder().subjectName("subj").build();
		} catch (ReportBuildException e) {
			System.err.println("null, null, subject - exception ok");
		}
		try {
			ReportBuilder.getBuilder().subjectName("subj").owner("nazzo").build();
		} catch (ReportBuildException e) {
			System.err.println("owner, null, subject - exception ok");
		}
		try {
			ReportBuilder.getBuilder().createdOn(new Date()).owner("nazzo").build();
		} catch (ReportBuildException e) {
			System.err.println("null, date, subject - exception ok");
		}
		ReportBuilder.getBuilder().subjectName("theSubject").createdOn(new Date()).owner("nazzo").build();
		System.out.println("end builder test");
	}
}
