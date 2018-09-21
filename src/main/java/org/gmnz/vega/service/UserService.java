package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;

import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 */
public interface UserService {

	List<Category> getAllUsers() throws VegaException;

	/*

	List<Category> getAllCategoriesWithAllergens() throws VegaException;


	Category getCategoryById(String id) throws VegaException;


	void createCategory(String name) throws VegaException;


	void changeCategoryName(String categoryId, String newCategoryName) throws VegaException;


	void removeCategory(String id) throws VegaException;

*/

}
