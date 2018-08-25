package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;

import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 * 18.824: trasformato in bean spring
 */
public class CategoryServiceImpl extends BasicServiceBean implements CategoryService {


	public CategoryServiceImpl(DaoFactory daoFactory) {
		super(daoFactory);
	}



	@Override
	public List<Category> getAllCategories() throws VegaException {
		try {
			CategoryDao dao = daoFactory.createCategoryDao();
			List<Category> categories = dao.findAll();
			return categories;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllCategories service error", e);
		}
	}



	@Override
	public List<Category> getAllCategoriesWithAllergens() throws VegaException {
		try {
			CategoryDao dao = daoFactory.createCategoryDao();
			List<Category> categories = dao.findAllWithAllergens();
			return categories;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllCategoriesWithAllergens service error", e);
		}
	}



	@Override
	public Category getCategoryById(String id) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(id)) {
			throw new VegaException("invalid id specified (null or empty)");
		}
		try {
			CategoryDao dao = daoFactory.createCategoryDao();
			Category c = dao.findById(id);
			return c;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getCategoryById service error", e);
		}
	}



	@Override
	public String createCategory(String name) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(name)) {
			throw new VegaException("null or empty category name");
		}
		try {
			CategoryDao dao = daoFactory.createCategoryDao();
			Category dupe = dao.findByName(name, false);
			if (dupe != null) {
				System.err.println("warning: category already exists for name \"" + name + "\"");
				return dupe.getId();
			} else {
				return dao.create(name);
			}
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createCategory service error", e);
		}

	}



	@Override
	public void changeCategoryName(String categoryId, String newCategoryName) throws VegaException {
		checkEntityRegistration(Category.class, newCategoryName, false);
		try {
			Category c = new Category(newCategoryName);
			c.setId(categoryId);
			CategoryDao dao = daoFactory.createCategoryDao();
			dao.update(c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("changeCategoryName service error", e);
		}
	}



	@Override
	public void removeCategory(String id) throws VegaException {
		try {
			CategoryDao dao = daoFactory.createCategoryDao();
			if (dao.countAllergens(id) == 0) {
				dao.delete(id);
			} else {
				throw new VegaException("Removing a non-empty category is not allowed");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("removeCategory service error", e);
		}
	}



	@Override
	public void deepRemove(String id) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(id)) {
			throw new VegaException("null or empty category id");
		}
		try {
			CategoryDao dao = daoFactory.createCategoryDao();
			dao.deepDelete(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("removeCategory service error", e);
		}

	}
}
