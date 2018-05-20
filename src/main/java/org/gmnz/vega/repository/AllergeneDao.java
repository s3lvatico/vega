package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;

import java.util.Collection;
import java.util.List;


public interface AllergeneDao {

	List<Allergen> findAll() throws DaoException;

	Allergen findByName(String name)throws DaoException;

	//List<Allergen> findByPattern(String pattern)throws DaoException;

	void create(Allergen allergen)throws DaoException;

	void create(Collection<Allergen> allergeni)throws DaoException;

	void delete(String nome)throws DaoException;

	void update(String nome, String newName)throws DaoException;
}
