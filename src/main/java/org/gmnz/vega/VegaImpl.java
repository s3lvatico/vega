package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.DummyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VegaImpl implements Vega {
	@Override
	public List<Allergen> getAllAllergens() {
		return new ArrayList<>(DummyRepository.getRegisteredAllergens());
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
	public List<Category> getAllCategories() {
		return new ArrayList<>(DummyRepository.getRegisteredCategories());
	}



	@Override
	public void createCategory(String nome) {
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
