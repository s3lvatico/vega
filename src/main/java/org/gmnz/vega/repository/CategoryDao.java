package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Category;

import java.util.List;


public interface CategoryDao extends ConnectionOrientedDao {

	List<Category> findAll() throws DaoException;



	List<Category> findAllWithAllergens() throws DaoException;



	Category findById(String id) throws DaoException;



	String create(String name) throws DaoException;



	void update(Category category) throws DaoException;



	int countAllergens(String categoryId) throws DaoException;



	void delete(String categoryId) throws DaoException;



	boolean isCategoryRegisteredByName(String name) throws DaoException;



	boolean isCategoryRegisteredById(String categoryId) throws DaoException;

}
