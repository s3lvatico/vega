package org.gmnz.vega.repository;


import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.Report;


public final class DummyRepository {

	private static final Set<Allergen> ALLERGENS = new HashSet<>();
	private static final Set<Category> CATEGORIES = new HashSet<>();
	private static final Set<Report> REPORTS = new HashSet<>();

//	private static final Map<Category, Set<Allergen>> CATEGORY_MAP = new HashMap<>();



	static {
		Category c = new Category("Carni");
		//	CATEGORY_MAP.put(c, new HashSet<>());
		CATEGORIES.add(c);

		Allergen a = new Allergen("Manzo");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);
		// CATEGORY_MAP.get(c).add(a);

		a = new Allergen("Pollo");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);
		//CATEGORY_MAP.get(c).add(a);

		a = new Allergen("Maiale");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);
		// CATEGORY_MAP.get(c).add(a);

		c = new Category("Cereali");
		CATEGORIES.add(c);
		//CATEGORY_MAP.put(c, new HashSet<>());

		a = new Allergen("Avena");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);
		// CATEGORY_MAP.get(c).add(a);
/*
		Category c = new Category("carni");
		Allergen a = new Allergen("manzo");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);
		CATEGORIES.add(c);

		a = new Allergen("pollo");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);

		a = new Allergen("maiale");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);

		c = new Category("cereali");
		CATEGORIES.add(c);

		a = new Allergen("avena");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);

		a = new Allergen("farina");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);

		a = new Allergen("mais");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);

		a = new Allergen("orzo");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);

		c = new Category("condimenti");
		CATEGORIES.add(c);

		a = new Allergen("lievito");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);

		a = new Allergen("olio di oliva");
		a.setCategory(c);
		c.add(a);
		ALLERGENS.add(a);
		*/

	}


	public static Collection<Allergen> getRegisteredAllergens() {
		return ALLERGENS;
	}



	public static Collection<Category> getRegisteredCategories() {
		//return Collections.unmodifiableCollection(CATEGORY_MAP.keySet());
		return Collections.unmodifiableCollection(CATEGORIES);
	}



	public static void addCategory(Category category) {
		//CATEGORY_MAP.put(category, new HashSet<>());
		CATEGORIES.add(category);
	}



	public static void removeCategory(Category category) {
		// CATEGORY_MAP.remove(category);
		CATEGORIES.remove(category);
	}



	public static Category getCategoryByName(String name) {
		for (Category c : CATEGORIES) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}



	public static Allergen getAllergenByName(String name) {
		for (Allergen c : ALLERGENS) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}



	public static void addAllergen(Allergen a) {
		ALLERGENS.add(a);
		for (Category c : CATEGORIES) {
			if (c.equals(a.getCategory())) {
				c.add(a);
				return;
			}
		}
		throw new RuntimeException("anomalous condition: no category found for allergen " + a);
	}



	public static void removeAllergen(String name) {
		// originale
		Allergen a = getAllergenByName(name);
		if (a != null) {
			// rimosso dall'insieme
			boolean removed = ALLERGENS.remove(a);
			if (removed) {
				// categoria dell'originale che è stato rimosso
				Category c0 = getCategoryByName(a.getCategory().getName());
				// nuova categoria, uguale all'altra
				// ma con elenco di allergeni diminuito
				// ricostruito perché la categoria ha la lista allergeni immutabile
				Category c = new Category(c0.getName());
				for (Allergen a0 : c0.getAllergens()) {
					if (!a0.equals(a)) {
						c.add(a0);
					}
				}
				CATEGORIES.remove(c);
				CATEGORIES.add(c);
			} else {
				System.err.format("warning: tried to remove allergen [%s] but it does not exist in the repository%n", name);
			}
		}
	}



	private static void removeAllergenFromCategory(String allergenName, String categoryName) {
		Category c = getCategoryByName(categoryName);
		Category c1 = new Category(categoryName);
		for (Allergen a : c.getAllergens()) {
			if (!a.getName().equals(allergenName)) {
				c1.add(a);
			}
		}
		CATEGORIES.remove(c);
		CATEGORIES.add(c1);
	}



	public static void renameAllergen(String fromName, String toName) {
		// ne va creato uno sostitutivo perché qui gli oggetti sono immutabili
		Allergen a0 = getAllergenByName(fromName);
		Allergen a1 = new Allergen(toName);
		a1.setCategory(a0.getCategory());
		removeAllergen(fromName);
		ALLERGENS.add(a1);
		for (Category c : CATEGORIES) {
			if (c.equals(a1.getCategory())) {
				c.add(a1);
				return;
			}
		}
	}



	public static void changeAllergenCategory(String allergenName, String targetCategoryName) {
		Allergen a = getAllergenByName(allergenName);
		removeAllergenFromCategory(allergenName, a.getCategory().getName());
		Category targetCategory = getCategoryByName(targetCategoryName);
		targetCategory.add(a);
		a.setCategory(targetCategory);
	}



	public static Collection<Report> getReports() {
		return Collections.unmodifiableCollection(REPORTS);
	}



	public static Report getReport(String subject, Date creationDate) {
		Report rr = new Report(subject, creationDate);
		for (Report r : REPORTS) {
			if (r.equals(rr)) {
				return r;
			}
		}
		return null;
	}



	public static Report getReportById(String id) {
		for (Report r : REPORTS) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}



	public static void addReport(Report r) {
		REPORTS.add(r);
	}
	
	public static void removeReport(Report r) {
		REPORTS.remove(r);
	}
}
