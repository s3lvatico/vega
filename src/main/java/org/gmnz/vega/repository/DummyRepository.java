package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public final class DummyRepository {

	private static final Set<Allergen> db = new HashSet<>();

	static {
		db.add(new Allergen("Farina"));
		db.add(new Allergen("Lievito"));
		db.add(new Allergen("Alcol"));
		db.add(new Allergen("Carne bovina"));
	}

	public static Collection<Allergen> getRegisteredAllergens() {
		return db;
	}
}
