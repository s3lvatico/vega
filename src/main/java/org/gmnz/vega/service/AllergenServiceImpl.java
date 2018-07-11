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
public class AllergenServiceImpl implements AllergenService {

	@Override
	public List<Allergen> getAll() {
		Collection<Allergen> registeredAllergens = DummyRepository.getRegisteredAllergens();
		List<Allergen> allergensList = new ArrayList<>(registeredAllergens);
		Collections.sort(allergensList, new AllergenComparator());
		return allergensList;

	}



	private void checkEntityRegistration(Class<?> clazz, String objectName, boolean mustBeInTheSystem)
			throws VegaException {

		boolean entityInTheSystem;
		switch (clazz.getSimpleName()) {
			case "Category":
				entityInTheSystem = DummyRepository.getCategoryByName(objectName) != null;
				break;
			case "Allergen":
				entityInTheSystem = DummyRepository.getAllergenByName(objectName) != null;
				break;
			default:
				throw new VegaException("anomalous condition occurred - cannot determine whether an entity is in the system");
		}
		if (mustBeInTheSystem ^ entityInTheSystem) {
			String errorMessage = String.format("%s [%s] was%s expected to be in the system but it is%s.", clazz.getSimpleName(),
					objectName, (mustBeInTheSystem ? "" : " not"), (entityInTheSystem ? "" : " not"));
			throw new VegaException(errorMessage);
		}
	}



	@Override
	public void createAllergen(String name, String categoryName) throws VegaException {
		checkEntityRegistration(Allergen.class, name, false);
		checkEntityRegistration(Category.class, categoryName, true);
		throw new RuntimeException("incomplete implementation");
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
	public void removeAllergen(String name) {
		throw new RuntimeException("not yet implemented");
	}
}
