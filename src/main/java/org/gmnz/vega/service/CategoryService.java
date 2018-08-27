package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;


/**
 * creato da simone in data 07/07/2018.
 */
public interface CategoryService {

	List<Category> getAllCategories() throws VegaException;



	List<Category> getAllCategoriesWithAllergens() throws VegaException;



	Category getCategoryById(String id) throws VegaException;



	String createCategory(String name) throws VegaException;



	void changeCategoryName(String categoryId, String newCategoryName) throws VegaException;



	void removeCategory(String id) throws VegaException;



	/**
	 * @deprecated è sbagliato introdurre metodi da usare solo per scopi di test in
	 *             un'interfaccia applicativa
	 * @param id id della categoria da rimuovere
	 * @throws VegaException se qualcosa va storto
	 */
	@Deprecated
	void deepRemove(String id) throws VegaException;

}
