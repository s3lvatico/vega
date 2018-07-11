package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;

import java.util.List;


public interface AllergenService {

	List<Allergen> getAll();


	void createAllergen(String name, String categoryName) throws VegaException;


	Allergen get(String name);


	void modifyAllergen(Allergen source, String targetName, String targetCategory) throws VegaException;


	void removeAllergen(String name) throws VegaException;

}
