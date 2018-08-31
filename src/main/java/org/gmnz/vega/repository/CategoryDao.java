package org.gmnz.vega.repository;


import java.util.List;

import org.gmnz.vega.domain.Category;


public interface CategoryDao {

	List<Category> findAll() throws DaoException;



	List<Category> findAllWithAllergens() throws DaoException;



	Category findById(String id) throws DaoException;



	Category findByName(String name, boolean deleted) throws DaoException;



	String create(String name) throws DaoException;



	void update(Category category) throws DaoException;



	int countAllergens(String categoryId) throws DaoException;



	void delete(String categoryId) throws DaoException;



	boolean isCategoryRegisteredByName(String name) throws DaoException;

}
