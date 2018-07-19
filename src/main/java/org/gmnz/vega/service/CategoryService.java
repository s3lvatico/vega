package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;

import java.util.List;


/**
 * creato da simone in data 07/07/2018.
 */
public interface CategoryService {

	List<Category> getAllCategories();



	void createCategory(String name);



	void renameCategory(String category, String newCategoryName) throws VegaException;



	void removeCategory(String name) throws VegaException;

}
