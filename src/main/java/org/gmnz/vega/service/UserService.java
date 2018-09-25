package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.User;


/**
 * creato da simone in data 07/07/2018.
 */
public interface UserService {

	List<User> getAllUsers() throws VegaException;



	User getUserById(String userId) throws VegaException;



	List<String> getAllRoles() throws VegaException;
	/*
	 * 
	 * List<Category> getAllCategoriesWithAllergens() throws VegaException;
	 * 
	 * 
	 * Category getCategoryById(String id) throws VegaException;
	 * 
	 * 
	 * void createCategory(String name) throws VegaException;
	 * 
	 * 
	 * void changeCategoryName(String categoryId, String newCategoryName) throws
	 * VegaException;
	 * 
	 * 
	 * void removeCategory(String id) throws VegaException;
	 * 
	 */

}
