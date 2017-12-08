package org.gmnz.vega.repository;

import org.gmnz.vega.domain.Allergene;

import java.util.List;

public interface AllergeneDao {

	List<Allergene> findAll();

	Allergene findByName(String name);

	List<Allergene> findByPattern(String pattern);

	void create(Object o); // TODO cambia

	// TODO completare
}
