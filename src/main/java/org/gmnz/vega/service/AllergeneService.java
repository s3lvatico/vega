package org.gmnz.vega.service;


import org.gmnz.vega.domain.Allergen;

import java.util.List;


public interface AllergeneService {

	List<Allergen> getAll();

	void create(String name);

	void get(String name);

	void rename(String oldName, String newName);

	void remove(String name);

}
