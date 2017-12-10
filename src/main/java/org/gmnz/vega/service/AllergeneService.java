package org.gmnz.vega.service;


import org.gmnz.vega.domain.Allergene;

import java.util.List;


public interface AllergeneService {

	List<Allergene> getAll();

	void create(String name);

	void get(String name);

	void rename(String oldName, String newName);

	void remove(String name);

}
