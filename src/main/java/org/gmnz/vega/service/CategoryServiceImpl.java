package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.DummyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 */
public class CategoryServiceImpl implements CategoryService {
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
	public void renameCategory(String oldName, String newCategoryName) throws VegaException {
		checkCategoryRegistration(oldName, true);
		checkCategoryRegistration(newCategoryName, false);

		Iterator<Category> iterator = DummyRepository.getRegisteredCategories().iterator();
		while (iterator.hasNext()) {
			Category c = iterator.next();
			if (c.getName().equals(oldName)) {
				if (c.getAllergens().size() == 0) {
					DummyRepository.removeCategory(new Category(oldName));
					DummyRepository.addCategory(new Category(newCategoryName));
					break;
				} else {
					throw new VegaException("renameCategory service error: a category must have no allergens associated in order to be renamed.");
				}
			}
		}
	}



	@Override
	public void removeCategory(String name) throws VegaException {
		checkCategoryRegistration(name, true);
		Iterator<Category> iterator = DummyRepository.getRegisteredCategories().iterator();
		while (iterator.hasNext()) {
			Category ic = iterator.next();
			if (ic.getName().equals(name)) {
				if (ic.getAllergens().size() == 0) {
					DummyRepository.removeCategory(ic);
					break;
				} else {
					throw new VegaException("removeCategory service error: a category must have no allergens associated in order to be deleted.");
				}
			}
		}
	}



	private void checkCategoryRegistration(String categoryName, boolean mustBeInTheSystem)
			throws VegaException {
		boolean categoryIsInTheSystem = DummyRepository.getCategoryByName(categoryName) != null;
		if (mustBeInTheSystem ^ categoryIsInTheSystem) {
			String errorMessage = String.format("Category [%s] was%s expected to be in the system but it is%s.",
					categoryName, (mustBeInTheSystem ? "" : " not"), (categoryIsInTheSystem ? "" : " not"));
			throw new VegaException(errorMessage);
		}
	}

}
