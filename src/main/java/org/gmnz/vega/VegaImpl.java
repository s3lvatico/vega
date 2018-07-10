package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.DummyRepository;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.service.AllergenServiceImpl;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.service.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class VegaImpl implements Vega {

	private final CategoryService categoryService;

	private final AllergenService allergenService;



	public VegaImpl() {
		categoryService = new CategoryServiceImpl();
		allergenService = new AllergenServiceImpl();
	}



	@Override
	public CategoryService getCategoryService() {
		return categoryService;
	}



	public AllergenService getAllergenService() {
		return allergenService;
	}






	@Override
	public void assignAllergenToCategory(String nomeAllergene, String nomeCategoria) {
		throw new RuntimeException("not yet implemented");
	}


}
