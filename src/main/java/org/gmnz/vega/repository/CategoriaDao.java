package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Category;

import java.util.List;


public interface CategoriaDao {

	List<Category> findAll()throws DaoException;

	Category findByName(String name)throws DaoException;

	void create(String nome)throws DaoException;

	void updateRename(String nome, String newName)throws DaoException;

	void updateAllergeni(Category category)throws DaoException;

	void delete(String nome)throws DaoException;

}
