package org.gmnz.vega.service;


import java.util.List;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;


public interface AllergenService {

	List<Allergen> getAllAllergens() throws VegaException;



	void createAllergen(String name, String categoryName) throws VegaException;



	Allergen get(String name);



	void modifyAllergen(Allergen source, String targetName, String targetCategory) throws VegaException;



	void removeAllergen(String name) throws VegaException;

}
