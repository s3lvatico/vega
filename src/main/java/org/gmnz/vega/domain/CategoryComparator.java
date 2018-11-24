package org.gmnz.vega.domain;


import java.util.Comparator;


public class CategoryComparator implements Comparator<Category> {

	@Override
	public int compare(Category o1, Category o2) {
		if (o1 == null) {
			if (o2 == null) {
				return 0;
			}
			else {
				return -1;
			}
		}
		else {
			if (o2 == null) {
				return 1;
			}
			else {
				return o1.getName().compareTo(o2.getName());
			}
		}

	}

}
