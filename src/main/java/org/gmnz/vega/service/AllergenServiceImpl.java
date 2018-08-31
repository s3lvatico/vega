package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.AllergenDao;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;


/**
 * creato da simone in data 10/07/2018.
 */
public class AllergenServiceImpl extends BasicServiceBean implements AllergenService {

	protected AllergenServiceImpl(DaoFactory daoFactory) {
		super(daoFactory);
	}



	@Override
	public List<Allergen> getAllAllergens() throws VegaException {
		AllergenDao dao = daoFactory.createAllergenDao();
		try {
			List<Allergen> allergens = dao.findAll();
			allergens.sort(new AllergenComparator());
			return allergens;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllAllergens service error", e);
		}
	}



	@Override
	public String createAllergen(String newAllergenName, String categoryId) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(newAllergenName) || VegaUtil.stringNullOrEmpty(categoryId)) {
			throw new VegaException("invalid allergen name or category id specified");
		}

		checkEntityRegistration(Allergen.class, newAllergenName, false);

		AllergenDao allergenDao = null;
		CategoryDao categoryDao = null;
		try {
			categoryDao = daoFactory.createCategoryDao();
			Category targetCategory = categoryDao.findById(categoryId);
			if (targetCategory == null) {
				String errorMessage = String.format("no category with id [%s]", categoryId);
				throw new VegaException(errorMessage);
			}

			Allergen a = new Allergen(newAllergenName);
			a.setCategory(targetCategory);

			allergenDao = daoFactory.createAllergenDao();
			return allergenDao.create(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createAllergen service error", e);
		}
	}



	@Override
	public Allergen getAllergenById(String id) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(id)) {
			throw new VegaException("null or empty allergen id ");
		}
		AllergenDao dao = null;
		try {
			dao = daoFactory.createAllergenDao();
			Allergen a = dao.findById(id);
			return a;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllergenById service error", e);
		}
	}



	@Override
	public void modifyAllergen(Allergen source, String targetName, String targetCategoryId) throws VegaException {
		boolean mustUpdateDb = false;
		if (!VegaUtil.stringNullOrEmpty(targetName) && !source.getName().equals(targetName)) {
			checkEntityRegistration(Allergen.class, targetName, false);
			source.setName(targetName);
			mustUpdateDb = true;
		}
		if (!VegaUtil.stringNullOrEmpty(targetCategoryId) && !source.getCategory().getId().equals(targetCategoryId)) {
			source.getCategory().setId(targetCategoryId);
			mustUpdateDb = true;
		}
		if (mustUpdateDb) {
			AllergenDao dao = null;
			try {
				dao = daoFactory.createAllergenDao();
				dao.update(source);
			} catch (DaoException e) {
				throw new VegaException("modifyAllergen service error", e);
			}
		}
	}



	public void changeCategory(Allergen source, String targetCategoryId) throws VegaException {
		modifyAllergen(source, null, targetCategoryId);
	}



	@Override
	public void renameAllergen(Allergen source, String newName) throws VegaException {
		modifyAllergen(source, newName, null);
	}



	@Override
	public void removeAllergen(String id) throws VegaException {
		if (!VegaUtil.stringNullOrEmpty(id)) {
			try {
				daoFactory.createAllergenDao().delete(id);
			} catch (DaoException e) {
				e.printStackTrace();
				throw new VegaException("removeAllergen service error", e);
			}
		}
	}

}
