package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.AllergenDao;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;

import java.util.List;


/**
 * creato da simone in data 10/07/2018.
 */
public class AllergenServiceImpl extends BasicServiceBean implements AllergenService {

	@Override
	public List<Allergen> getAllAllergens() throws VegaException {
		AllergenDao dao = null;
		try {
			dao = DaoFactory.getInstance().createAllergenDao();
			List<Allergen> allergens = dao.findAll();
			allergens.sort(new AllergenComparator());
			return allergens;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllAllergens service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void createAllergen(String newAllergenName, String categoryId) throws VegaException {
		checkEntityRegistration(Allergen.class, newAllergenName, false);

		AllergenDao allergenDao = null;
		CategoryDao categoryDao = null;
		try {
			categoryDao = DaoFactory.getInstance().createCategoryDao();
			Category targetCategory = categoryDao.findById(categoryId);
			if (targetCategory == null) {
				String errorMessage = String.format("no category with id [%s]", categoryId);
				throw new VegaException(errorMessage);
			}

			Allergen a = new Allergen(newAllergenName);
			a.setCategory(targetCategory);

			allergenDao = DaoFactory.getInstance().createAllergenDao();
			allergenDao.create(a);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllAllergens service error", e);
		}
		finally {
			finalizeDao(categoryDao);
			finalizeDao(allergenDao);
		}
	}



	@Override
	public Allergen getAllergenById(String id) throws VegaException {
		AllergenDao dao = null;
		try {
			dao = DaoFactory.getInstance().createAllergenDao();
			Allergen a = dao.findById(id);
			return a;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllergenById service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void modifyAllergen(Allergen source, String targetName, String targetCategoryId) throws VegaException {
		boolean mustUpdateDb = false;
		if (!source.getName().equals(targetName)) {
			checkEntityRegistration(Allergen.class, targetName, false);
			source.setName(targetName);
			mustUpdateDb = true;
		}
		if (!source.getCategory().getId().equals(targetCategoryId)) {
			source.getCategory().setId(targetCategoryId);
			mustUpdateDb = true;
		}
		if (mustUpdateDb) {
			AllergenDao dao = null;
			try {
				dao = DaoFactory.getInstance().createAllergenDao();
				dao.update(source);
			}
			catch (DaoException e) {
				throw new VegaException("modifyAllergen service error", e);
			}
			finally {
				finalizeDao(dao);
			}
		}
	}



	@Override
	public void removeAllergen(String id) throws VegaException {
		AllergenDao dao = null;
		try {
			dao = DaoFactory.getInstance().createAllergenDao();
			dao.delete(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("removeAllergen service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}

}
