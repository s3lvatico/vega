package org.gmnz.vega.service;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.DummyRepository;


/**
 * creato da simone in data 07/07/2018.
 */
public class CategoryServiceImpl extends BasicServiceBean implements CategoryService {

	@Override
	public List<Category> getAllCategories() throws VegaException {
		try {
			CategoryDao categoryDao = DaoFactory.getInstance().createCategoryDao();
			List<Category> categories = categoryDao.findAll();
			return categories;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllCategories service error", e);
		}
	}



	// TODO createCategory use the DAO
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



	// TODO renameCategory use the DAO
	@Override
	public void renameCategory(String oldName, String newCategoryName) throws VegaException {
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



	// TODO removeCategory use the DAO
	// TODO removeCategory there can be the default category
	@Override
	public void removeCategory(String name) throws VegaException {
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
