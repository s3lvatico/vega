package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.*;

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
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllAllergens service error", e);
		} finally {
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
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getAllAllergens service error", e);
		} finally {
			finalizeDao(categoryDao);
			finalizeDao(allergenDao);
		}
	}



	@Deprecated
	@Override
	public Allergen get(String name) {
		return DummyRepository.getAllergenByName(name);
	}



	@Override
	public Allergen getAllergenById(String id) throws VegaException {
		// TODO fare AllergenServiceImpl.getAllergenById
		throw new VegaException("not yet implemented");
	}



	@Override
	public void modifyAllergen(Allergen source, String targetName, String targetCategory) throws VegaException {
		checkEntityRegistration(Allergen.class, source.getName(), true);
		checkEntityRegistration(Category.class, targetCategory, true);
		if (!source.getName().equals(targetName)) {
			checkEntityRegistration(Allergen.class, targetName, false);
			DummyRepository.renameAllergen(source.getName(), targetName);
		}
		if (!source.getCategory().getName().equals(targetCategory)) {
			DummyRepository.changeAllergenCategory(source.getName(), targetCategory);
		}
	}



	@Override
	public void removeAllergen(String name) throws VegaException {
		checkEntityRegistration(Allergen.class, name, true);
		DummyRepository.removeAllergen(name);
	}
}
