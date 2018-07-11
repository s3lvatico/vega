package org.gmnz.vega.domain;


public class ToxicityRating {

	private Allergen allergen;

	private double toxicity;



	public ToxicityRating(Allergen a, double toxicity) {
		allergen = a;
		this.toxicity = toxicity;
	}



	public Allergen getAllergen() {
		return allergen;
	}



	public double getToxicity() {
		return toxicity;
	}



	@Override
	public String toString() {
		return "ToxicityRating{" + "allergen=" + allergen + ", toxicity=" + toxicity + '}';
	}
}
