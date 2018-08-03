package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;

import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 */
public interface CategoryService {

	List<Category> getAllCategories() throws VegaException;



	Category getCategoryById(String id) throws VegaException;



	void createCategory(String name) throws VegaException;



	/**
	 * @param category
	 * @param newCategoryName
	 * @throws VegaException
	 * @deprecated use {@link #changeCategoryName(String, String)} instead.
	 */
	@Deprecated
	void renameCategory(String category, String newCategoryName) throws VegaException;



	void changeCategoryName(String categoryId, String newCategoryName) throws VegaException;



	void removeCategory(String id) throws VegaException;

}
