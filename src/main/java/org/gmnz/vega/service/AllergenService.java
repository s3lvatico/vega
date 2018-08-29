package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;


public interface AllergenService {

	List<Allergen> getAllAllergens() throws VegaException;



	String createAllergen(String newAllergenName, String categoryId) throws VegaException;



	Allergen getAllergenById(String id) throws VegaException;



	void modifyAllergen(Allergen source, String targetName, String targetCategoryId) throws VegaException;



	void changeCategory(Allergen source, String targetCategoryId) throws VegaException;



	void renameAllergen(Allergen source, String newName) throws VegaException;



	void removeAllergen(String id) throws VegaException;

}
