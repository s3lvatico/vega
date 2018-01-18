package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Categoria;

import java.util.List;


public interface CategoriaDao {

	List<Categoria> findAll()throws DaoException;

	Categoria findByName(String name)throws DaoException;

	void create(Categoria categoria)throws DaoException;

	void updateRename(String nome, String newName)throws DaoException;

	void updateAllergeni(Categoria categoria)throws DaoException;

	void delete(String nome)throws DaoException;

}
