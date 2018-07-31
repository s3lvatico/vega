package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;


/**
 * creato da simone in data 07/07/2018.
 */
public interface CategoryService {

	List<Category> getAllCategories() throws VegaException;



	void createCategory(String name);



	void renameCategory(String category, String newCategoryName) throws VegaException;



	void removeCategory(String name) throws VegaException;

}
