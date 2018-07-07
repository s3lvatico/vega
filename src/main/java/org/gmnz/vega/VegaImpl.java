package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.DummyRepository;

import java.util.*;


public class VegaImpl implements Vega {

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
	public List<Category> getAllCategories() {
		return new ArrayList<>(DummyRepository.getRegisteredCategories());
	}



	@Override
	public void createCategory(String nome) {
		Collection<Category> registeredCategories = DummyRepository.getRegisteredCategories();
		Category c = new Category(nome);
		if (!registeredCategories.contains(c)) {
			DummyRepository.addCategory(c);
		} else {
			System.err.println("category already present");
		}
	}



	@Override
	public Category selectCategory(String nome) {
		return null;
	}



	@Override
	public void assignAllergenToCategory(String nomeAllergene, String nomeCategoria) {
		throw new RuntimeException("not yet implemented");
	}



	@Override
	public void renameCategory(String category, String newCategoryName) throws VegaException {
		Collection<Category> registeredCategories = DummyRepository.getRegisteredCategories();
		Category oc = new Category(category);
		if (!registeredCategories.contains(oc)) {
			throw new VegaException("requested category is not registered in the system");
		}
		Category nc = new Category(newCategoryName);
		if (registeredCategories.contains(nc)) {
			String errorMessage = String.format("There is already a category named <%s> in the system", newCategoryName);
			throw new VegaException(errorMessage);
		}

		Iterator<Category> iterator = registeredCategories.iterator();
		while (iterator.hasNext()) {
			Category c = iterator.next();
			if (c.getName().equals(category)) {
				if (c.getAllergens().size() == 0) {
					registeredCategories.remove(oc);
					registeredCategories.add(nc);
					break;
				}
				else {
					throw new VegaException("a category must have no allergens associated in order to be renamed.");
				}
			}
		}
	}



	@Override
	public void removeCategory(String name) throws VegaException {
		Collection<Category> registeredCategories = DummyRepository.getRegisteredCategories();
		Category oc = new Category(name);
		if (!registeredCategories.contains(oc)) {
			throw new VegaException("requested category is not registered in the system");
		}


	}

}
