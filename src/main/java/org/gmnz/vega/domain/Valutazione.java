package org.gmnz.vega.domain;


public class Valutazione {

	private Allergene allergene;

	private double tossicita;



	public Valutazione(Allergene a, double tossicita) {
		allergene = a;
		this.tossicita = tossicita;
	}



	public Allergene getAllergene() {
		return allergene;
	}



	public double getTossicita() {
		return tossicita;
	}



	@Override
	public String toString() {
		return "Valutazione{" +
				"allergene=" + allergene +
				", tossicita=" + tossicita +
				'}';
	}
}
