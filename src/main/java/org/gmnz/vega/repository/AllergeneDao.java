package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergene;

import java.util.Collection;
import java.util.List;


public interface AllergeneDao {

	List<Allergene> findAll() throws DaoException;

	Allergene findByName(String name)throws DaoException;

	//List<Allergene> findByPattern(String pattern)throws DaoException;

	void create(Allergene allergene)throws DaoException;

	void create(Collection<Allergene> allergeni)throws DaoException;

	void delete(String nome)throws DaoException;

	void update(String nome, String newName)throws DaoException;
}
