package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;

import java.util.List;


public interface AllergenDao extends ConnectionOrientedDao {

	List<Allergen> findAll() throws DaoException;


	Allergen findById(String id) throws DaoException;


	boolean isAllergenRegisteredByName(String name) throws DaoException;


	String create(Allergen allergen) throws DaoException;


	void update(Allergen allergen) throws DaoException;


	void delete(String id) throws DaoException;

}
