package org.gmnz.vega.repository;


import java.util.List;

import org.gmnz.vega.domain.Category;


public interface CategoryDao {

	List<Category> findAll() throws DaoException;



	Category findByName(String name) throws DaoException;



	void create(String name) throws DaoException;



	@Deprecated // TODO forse da rimuovere più in là
	void updateRename(String oldName, String newName) throws DaoException;



	void update(Category category) throws DaoException;



	void updateAllergeni(Category category) throws DaoException;



	void delete(String name) throws DaoException;



	boolean isCategoryRegistered(String name) throws DaoException;

}
