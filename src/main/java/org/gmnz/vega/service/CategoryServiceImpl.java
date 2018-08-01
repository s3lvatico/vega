package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;


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
			if (!dao.isCategoryRegistered(name)) {
				dao.create(name);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createCategory service error", e);
		}
	}



	@Override
	public void renameCategory(String oldName, String newCategoryName) throws VegaException {
		checkEntityRegistration(Category.class, oldName, true);
		checkEntityRegistration(Category.class, newCategoryName, false);
		try {
			DaoFactory.getInstance().createCategoryDao().updateRename(oldName, newCategoryName);
		} catch (DaoException e) {
			e.printStackTrace();
			String errorMessage = String.format("renameCategory service error - can't rename category [%s] to [%s]",
					oldName, newCategoryName);
			throw new VegaException(errorMessage, e);
		}
	}



	@Override
	public void removeCategory(String name) throws VegaException {
		checkEntityRegistration(Category.class, name, true);
		try {
			DaoFactory.getInstance().createCategoryDao().delete(name);
		} catch (DaoException e) {
			e.printStackTrace();
			String errorMessage = String.format("removeCategory service error - can't remove category [%s]", name);
			throw new VegaException(errorMessage, e);
		}
	}

}
