package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class AllergenDaoImpl extends BasicDaoImpl implements AllergenDao {

	@Override
	public List<Allergen> findAll() throws DaoException {
		Collection<Allergen> registeredAllergens = DummyRepository.getRegisteredAllergens();
		return new ArrayList<>(registeredAllergens);
	}



	@Override
	public Allergen findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void create(Allergen allergen) throws DaoException {
		DummyRepository.addAllergen(allergen);
	}



	@Override
	public void create(Collection<Allergen> allergens) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void delete(String name) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void update(String oldName, String newName) throws DaoException {
		// TODO Auto-generated method stub

	}

}
