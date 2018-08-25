package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Category;

import java.util.List;


public interface CategoryDao /* extends ConnectionOrientedDao */ {

	List<Category> findAll() throws DaoException;

	List<Category> findAllWithAllergens() throws DaoException;

	Category findById(String id) throws DaoException;

	Category findByName(String name, boolean deleted) throws DaoException;

	String create(String name) throws DaoException;

	void update(Category category) throws DaoException;

	int countAllergens(String categoryId) throws DaoException;

	void delete(String categoryId) throws DaoException;

	boolean isCategoryRegisteredByName(String name) throws DaoException;

	/**
	 * @deprecated non si usa più
	 * @param categoryId id da controllare
	 * @return true se esiste, altrimenti false
	 * @throws DaoException se qualcosa va storto
	 */
	@Deprecated
	boolean isCategoryRegisteredById(String categoryId) throws DaoException;

	/**
	 * Elimina fisicame
	 * @param categoryId
	 * @throws DaoException
	 */
	void deepDelete(String categoryId) throws DaoException;
}
