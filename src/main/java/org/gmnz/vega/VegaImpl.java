package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.DummyRepository;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.service.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class VegaImpl implements Vega {

	private final CategoryService categoryService;



	public VegaImpl() {
		categoryService = new CategoryServiceImpl();
	}



	@Override
	public CategoryService getCategoryService() {
		return categoryService;
	}



	@Override
	public List<Allergen> getAllAllergens() {
		Collection<Allergen> registeredAllergens = DummyRepository.getRegisteredAllergens();
		List<Allergen> allergensList = new ArrayList<>(registeredAllergens);
		Collections.sort(allergensList, new AllergenComparator());
		return allergensList;
	}



	@Override
	public void createAllergen(String name) {
		throw new RuntimeException("not yet implemented");
	}



	@Override
	public Allergen selectAllergen(String nome) {
		return null;
	}



	@Override
	public void renameAllergen(String vecchioNome, String nuovoNome) {
		throw new RuntimeException("not yet implemented");
	}



	@Override
	public void removeAllergen(String nome) {
		throw new RuntimeException("not yet implemented");
	}



	@Override
	public Category selectCategory(String nome) {
		return null;
	}



	@Override
	public void assignAllergenToCategory(String nomeAllergene, String nomeCategoria) {
		throw new RuntimeException("not yet implemented");
	}


}
