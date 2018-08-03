package org.gmnz.vega.repository;


import java.util.Collection;
import java.util.List;

import org.gmnz.vega.domain.Allergen;


public interface AllergenDao extends ConnectionOrientedDao {

	List<Allergen> findAll() throws DaoException;



	Allergen findByName(String name) throws DaoException;



	void create(Allergen allergen) throws DaoException;



	void create(Collection<Allergen> allergens) throws DaoException;



	void delete(String name) throws DaoException;



	void update(String oldName, String newName) throws DaoException;

}
