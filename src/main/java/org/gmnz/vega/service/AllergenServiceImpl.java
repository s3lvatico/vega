package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.DummyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * creato da simone in data 10/07/2018.
 */
public class AllergenServiceImpl extends BasicServiceBean implements AllergenService {

	@Override
	public List<Allergen> getAll() {
		Collection<Allergen> registeredAllergens = DummyRepository.getRegisteredAllergens();
		List<Allergen> allergensList = new ArrayList<>(registeredAllergens);
		Collections.sort(allergensList, new AllergenComparator());
		return allergensList;

	}



	@Override
	public void createAllergen(String name, String categoryName) throws VegaException {
		checkEntityRegistration(Allergen.class, name, false);
		checkEntityRegistration(Category.class, categoryName, true);

		Category c = DummyRepository.getCategoryByName(categoryName);
		Allergen a = new Allergen(name);
		a.setCategory(c);

		DummyRepository.addAllergen(a);
	}



	@Override
	public void get(String name) {
		throw new RuntimeException("not yet implemented");
	}



	@Override
	public void renameAllergen(String oldName, String newName) {
		throw new RuntimeException("not yet implemented");
	}



	@Override
	public void removeAllergen(String name) throws VegaException {
		checkEntityRegistration(Allergen.class, name, true);
		DummyRepository.removeAllergen(name);
	}
}
