package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Categoria;

import java.util.List;


public interface CategoriaDao {

	List<Categoria> findAll();

	Categoria findByName(String name);

	List<Categoria> findByPattern(String pattern);

	void create(Categoria categoria);

	void delete(String nome);

	void update(String nome, String newName);
}
