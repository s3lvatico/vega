package org.gmnz.vega.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.DummyRepository;


/**
 * creato da simone in data 07/07/2018.
 */
public class CategoryServiceImpl extends BasicServiceBean implements CategoryService {

	@Override
	public List<Category> getAllCategories() {
		// TODO getAllCategories deve referenziare i dao, non il repository
		return new ArrayList<>(DummyRepository.getRegisteredCategories());
	}



	@Override
	public void createCategory(String nome) {
		// TODO createCategory deve referenziare i dao, non il repository
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
		// TODO renameCategory deve referenziare i dao, non il repository
		checkEntityRegistration(Category.class, oldName, true);
		checkEntityRegistration(Category.class, newCategoryName, false);

		Iterator<Category> iterator = DummyRepository.getRegisteredCategories().iterator();
		while (iterator.hasNext()) {
			Category c = iterator.next();
			if (c.getName().equals(oldName)) {
				if (c.getAllergens().size() == 0) {
					DummyRepository.removeCategory(new Category(oldName));
					DummyRepository.addCategory(new Category(newCategoryName));
					break;
				} else {
					throw new VegaException(
							"renameCategory service error: a category must have no allergens associated in order to be renamed.");
				}
			}
		}
	}



	@Override
	public void removeCategory(String name) throws VegaException {
		// TODO removeCategory deve referenziare i dao, non il repository
		checkEntityRegistration(Category.class, name, true);
		Iterator<Category> iterator = DummyRepository.getRegisteredCategories().iterator();
		while (iterator.hasNext()) {
			Category ic = iterator.next();
			if (ic.getName().equals(name)) {
				if (ic.getAllergens().size() == 0) {
					DummyRepository.removeCategory(ic);
					break;
				} else {
					throw new VegaException(
							"removeCategory service error: a category must have no allergens associated in order to be deleted.");
				}
			}
		}
	}

}
