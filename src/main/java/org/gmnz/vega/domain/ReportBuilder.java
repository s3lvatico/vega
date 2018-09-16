package org.gmnz.vega.domain;


import java.util.Calendar;
import java.util.Date;


public class ReportBuilder {

	private String subjectName;

	private Date createdOn;

	private String owner;



	private ReportBuilder() {
		this.subjectName = null;
		this.createdOn = null;
		this.owner = null;
	}



	public static ReportBuilder getBuilder() {
		return new ReportBuilder();
	}



	public ReportBuilder subjectName(String subjectName) {
		this.subjectName = subjectName;
		return this;
	}



	public ReportBuilder createdOn(Date createdOn) {
		/*
		 * Normalizza i millisecondi e li azzera. Mentre i database classici memorizzano
		 * i tipi timestamp e datetime con 6 cifre decimali per indicare le frazioni di
		 * secondo, MySql ne usa ZERO, per questioni di retrocompatibilità.
		 *
		 * Se si memorizza un long che rappresenta un timestamp, in questo modo, vengono
		 * semplicemente troncate le ultime tre cifre, che rappresentano la parte
		 * frazionaria dei secondi.
		 *
		 * Una soluzione è quella di intervenire sullo schema del db. Un'altra soluzione
		 * è la presente normalizzazione, che anche volendo usare altri tipi di db non
		 * lede la generalità
		 */
		Calendar c = Calendar.getInstance();
		c.setTime(createdOn);
		c.set(Calendar.MILLISECOND, 0);
		this.createdOn = c.getTime();
		return this;
	}



	public ReportBuilder owner(String owner) {
		this.owner = owner;
		return this;
	}



	public Report build() throws ReportBuildException {
		checkIfBuildable();
		return new Report(subjectName, createdOn, owner);
	}



	private void checkIfBuildable() throws ReportBuildException {
		if (subjectName == null || createdOn == null || owner == null) {
			throw new ReportBuildException("not enough properties specified for report construction");
		}
	}

}
