package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Allergen;

import java.util.List;


public interface AllergenService {

	List<Allergen> getAll();


	void createAllergen(String name, String categoryName) throws VegaException;


	void get(String name);


	void renameAllergen(String oldName, String newName) throws VegaException;


	void removeAllergen(String name) throws VegaException;

}
