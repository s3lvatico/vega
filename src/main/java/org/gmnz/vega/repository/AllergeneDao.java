package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergene;

import java.util.Collection;
import java.util.List;


public interface AllergeneDao {

	List<Allergene> findAll();

	Allergene findByName(String name);

	List<Allergene> findByPattern(String pattern);

	void create(Allergene allergene);

	void create(Collection<Allergene> allergeni);

	void delete(String nome);

	void update(String nome, String newName);
}
