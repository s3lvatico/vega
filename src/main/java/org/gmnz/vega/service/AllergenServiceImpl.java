package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.AllergenComparator;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.AllergenDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.DummyRepository;


/**
 * creato da simone in data 10/07/2018.
 */
public class AllergenServiceImpl extends BasicServiceBean implements AllergenService {

	@Override
	public List<Allergen> getAllAllergens() throws VegaException {
		try {
			AllergenDao dao = DaoFactory.getInstance().createAllergenDao();
			List<Allergen> allergens = dao.findAll();
			allergens.sort(new AllergenComparator());
			return allergens;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("AllergenService.getAll() service error", e);
		}
	}



	@Override
	public void createAllergen(String name, String categoryName) throws VegaException {
		checkEntityRegistration(Allergen.class, name, false);
		checkEntityRegistration(Category.class, categoryName, true);

		Category c = DummyRepository.getCategoryByName(categoryName);
		Allergen a = new Allergen(name);
		a.setCategory(c);

		try {
			AllergenDao dao = DaoFactory.getInstance().createAllergenDao();
			dao.create(a);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("AllergenService.createAllergen() service error", e);
		}


	}



	@Override
	public Allergen get(String name) {
		return DummyRepository.getAllergenByName(name);
	}



	@Override
	public void modifyAllergen(Allergen source, String targetName, String targetCategory) throws VegaException {
		// TODO modifyAllergen deve referenziare il dao e non il repository
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
		// TODO removeAllergen deve referenziare il dao e non il repository
		checkEntityRegistration(Allergen.class, name, true);
		DummyRepository.removeAllergen(name);
	}
}
