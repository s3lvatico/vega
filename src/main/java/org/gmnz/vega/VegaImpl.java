package org.gmnz.vega;


import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.service.AllergenServiceImpl;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.service.CategoryServiceImpl;


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
