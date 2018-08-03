package org.gmnz.vega.service;


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



	@Override
	public Category getCategoryById(String id) throws VegaException {
		try {
			CategoryDao dao = DaoFactory.getInstance().createCategoryDao();
			Category c = dao.findById(id);
			return c;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getCategoryById service error", e);
		}
	}



	@Override
	public void createCategory(String name) throws VegaException {
		try {
			checkEntityRegistration(Category.class, name, false);
		} catch (VegaException e) {
			// it just tells me the category is in the system
			return;
		}
		DaoFactory daoFactory = DaoFactory.getInstance();
		try {
			CategoryDao dao = daoFactory.createCategoryDao();
			dao.create(name);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createCategory service error", e);
		}
	}



	// TODO verosimilmente da rimuovere
	@Override
	@Deprecated
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



	@Override
	public void changeCategoryName(String categoryId, String newCategoryName) throws VegaException {
		try {
			CategoryDao dao = DaoFactory.getInstance().createCategoryDao();
			boolean targetCategoryExists = dao.isCategoryRegistered(newCategoryName);
			if (targetCategoryExists) {
				String errorMessage = String
						.format("changeCategoryName service error - specified category already exists [%s]", newCategoryName);
				throw new VegaException(errorMessage);
			}
			Category c = new Category(newCategoryName);
			c.setId(categoryId);
			dao.update(c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("changeCategoryName service error", e);
		}

	}



	// TODO removeCategory use the DAO
	// TODO removeCategory there can be the default category
	@Override
	public void removeCategory(String id) throws VegaException {
		// TODO fare
	}

}
