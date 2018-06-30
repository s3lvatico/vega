package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;

import java.util.List;


public interface Vega {

	List<Allergen> getAllAllergens();

	void createAllergen(String name);

	Allergen selectAllergen(String name);

	void renameAllergen(String allergenName, String newName);

	void removeAllergen(String name);

	List<Category> getAllCategories();

	void createCategory(String name);

	Category selectCategory(String name);

	void assignAllergenToCategory(String allergenName, String categoryName);


}
