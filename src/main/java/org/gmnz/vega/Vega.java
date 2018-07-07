package org.gmnz.vega;


import java.util.List;

import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.CategoryService;


public interface Vega {

	List<Allergen> getAllAllergens();



	void createAllergen(String name);



	Allergen selectAllergen(String name);



	void renameAllergen(String allergenName, String newName);



	void removeAllergen(String name);



	Category selectCategory(String name);



	void assignAllergenToCategory(String allergenName, String categoryName);


	CategoryService getCategoryService();


}
