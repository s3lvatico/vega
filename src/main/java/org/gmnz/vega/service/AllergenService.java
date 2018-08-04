package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;

import java.util.List;


public interface AllergenService {

	List<Allergen> getAllAllergens() throws VegaException;

	void createAllergen(String newAllergenName, String categoryId) throws VegaException;

	Allergen getAllergenById(String id) throws VegaException;

	void modifyAllergen(Allergen source, String targetName, String targetCategoryId) throws VegaException;

	void removeAllergen(String id) throws VegaException;

}
