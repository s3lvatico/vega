package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;


/**
 * creato da simone in data 07/07/2018. 18.824: trasformato in bean spring
 */
public class CategoryServiceImpl extends BasicServiceBean implements CategoryService {

	public CategoryServiceImpl(DaoFactory daoFactory) {
		super(daoFactory);
	}



	@Override
	public List<Category> getAllCategories() throws VegaException {
		CategoryDao dao = daoFactory.createCategoryDao();
		try {
			return dao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllCategories service error", e);
		}
	}



	@Override
	public List<Category> getAllCategoriesWithAllergens() throws VegaException {
		CategoryDao dao = daoFactory.createCategoryDao();
		try {
			return dao.findAllWithAllergens();
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
		CategoryDao dao = daoFactory.createCategoryDao();
		try {
			return dao.findById(id);
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
		CategoryDao dao = daoFactory.createCategoryDao();
		try {
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
		if (VegaUtil.stringNullOrEmpty(categoryId) || VegaUtil.stringNullOrEmpty(newCategoryName)) {
			throw new VegaException("target category id and new category name must be valid");
		}
		checkEntityRegistration(Category.class, newCategoryName, false);
		Category c = new Category(newCategoryName);
		c.setId(categoryId);
		CategoryDao dao = daoFactory.createCategoryDao();
		try {
			dao.update(c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("changeCategoryName service error", e);
		}
	}



	@Override
	public void removeCategory(String id) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(id)) {
			throw new VegaException("null or empty category id");
		}
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



	@Deprecated
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
