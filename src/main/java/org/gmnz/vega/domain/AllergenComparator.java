package org.gmnz.vega.domain;


import java.util.Comparator;


public class AllergenComparator implements Comparator<Allergen> {

	@Override
	public int compare(Allergen o1, Allergen o2) {
		int categoryCompare = new CategoryComparator().compare(o1.getCategory(), o2.getCategory());
		if (categoryCompare == 0) {
			return o1.getName().compareTo(o2.getName());
		}
		else {
			return categoryCompare;
		}
	}

}
