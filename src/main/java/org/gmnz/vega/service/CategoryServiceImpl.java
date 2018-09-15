package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;

import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 */
public class CategoryServiceImpl extends BasicServiceBean implements CategoryService {

	@Override
	public List<Category> getAllCategories() throws VegaException {
		CategoryDao dao = null;
		try {
			dao = DaoFactory.getInstance().createCategoryDao();
			List<Category> categories = dao.findAll();
			return categories;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllCategories service error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	@Override
	public List<Category> getAllCategoriesWithAllergens() throws VegaException {
		CategoryDao dao = null;
		try {
			dao = DaoFactory.getInstance().createCategoryDao();
			List<Category> categories = dao.findAllWithAllergens();
			return categories;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllCategoriesWithAllergens service error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	@Override
	public Category getCategoryById(String id) throws VegaException {
		CategoryDao dao = null;
		try {
			dao = DaoFactory.getInstance().createCategoryDao();
			Category c = dao.findById(id);
			return c;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getCategoryById service error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void createCategory(String name) throws VegaException {
		try {
			checkEntityRegistration(Category.class, name, false);
		} catch (VegaException e) {
			// it just tells me the category is in the system
			// no need to create anything
			return;
		}
		CategoryDao dao = null;
		try {
			dao = DaoFactory.getInstance().createCategoryDao();
			dao.create(name);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createCategory service error", e);
		} finally {
			finalizeDao(dao);
		}
	}

//	@Override
//	@Deprecated
//	public void renameCategory(String oldName, String newCategoryName) throws VegaException {
//		checkEntityRegistration(Category.class, oldName, true);
//		checkEntityRegistration(Category.class, newCategoryName, false);
//
//		Iterator<Category> iterator = DummyRepository.getRegisteredCategories().iterator();
//		while (iterator.hasNext()) {
//			Category c = iterator.next();
//			if (c.getName().equals(oldName)) {
//				if (c.getAllergens().size() == 0) {
//					DummyRepository.removeCategory(new Category(oldName));
//					DummyRepository.addCategory(new Category(newCategoryName));
//					break;
//				} else {
//					throw new VegaException(
//							"renameCategory service error: a category must have no allergens associated in order to be renamed.");
//				}
//			}
//		}
//	}



	@Override
	public void changeCategoryName(String categoryId, String newCategoryName) throws VegaException {
		checkEntityRegistration(Category.class, newCategoryName, false);
		CategoryDao dao = null;
		try {
			Category c = new Category(newCategoryName);
			c.setId(categoryId);
			dao = DaoFactory.getInstance().createCategoryDao();
			dao.update(c);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("changeCategoryName service error", e);
		} finally {
			finalizeDao(dao);
		}

	}



	@Override
	public void removeCategory(String id) throws VegaException {
		CategoryDao dao = null;
		try {
			dao = DaoFactory.getInstance().createCategoryDao();
			if (dao.countAllergens(id) == 0) {
				dao.delete(id);
			} else {
				throw new VegaException("Removing a non-empty category is not allowed");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("removeCategory service error", e);
		} finally {
			finalizeDao(dao);
		}
	}

}
