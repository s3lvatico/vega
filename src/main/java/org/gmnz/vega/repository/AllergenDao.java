package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;

import java.util.List;


public interface AllergenDao extends ConnectionOrientedDao {

	List<Allergen> findAll() throws DaoException;

	Allergen findById(String id) throws DaoException;

	boolean isAllergenRegisteredByName(String name) throws DaoException;

	void create(Allergen allergen) throws DaoException;

	// TODO AllergenDao.delete sarà una cancellazione per id
	void delete(String name) throws DaoException;

	// TODO AllergenDao.update sarà una modifica più elaborata
	void update(String oldName, String newName) throws DaoException;

}
