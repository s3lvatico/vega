package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public final class DummyRepository {

	private static final Set<Allergen> ALLERGENS = new HashSet<>();
	private static final Set<Category> CATEGORIES = new HashSet<>();

	static {

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
	}


	public static Collection<Allergen> getRegisteredAllergens() {
		return ALLERGENS;
	}



	public static Collection<Category> getRegisteredCategories() {
		return CATEGORIES;
	}



	public static void addCategory(Category category) {
		CATEGORIES.add(category);
	}



	public static void removeCategory(Category category) {
		CATEGORIES.remove(category);
	}



	public static Category getCategoryByName(String name) {
		for (Category c : CATEGORIES) {
			if (c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}

	public static Allergen getAllergenByName(String name) {
		for (Allergen c : ALLERGENS) {
			if (c.getName().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}
}
