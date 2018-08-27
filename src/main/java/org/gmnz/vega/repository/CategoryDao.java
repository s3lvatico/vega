package org.gmnz.vega.repository;


import java.util.List;

import org.gmnz.vega.domain.Category;


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
	 * Elimina fisicamente un record.
	 * 
	 * Questo può introdurre inconsistenze.
	 * 
	 * @deprecated da non usare e da rimuovere in futuro
	 * @param categoryId
	 * @throws DaoException
	 */
	@Deprecated
	void deepDelete(String categoryId) throws DaoException;
}
