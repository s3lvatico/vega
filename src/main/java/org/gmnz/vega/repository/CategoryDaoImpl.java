package org.gmnz.vega.repository;

import java.util.List;

import org.gmnz.vega.domain.Category;


class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Category findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void create(String name) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void updateRename(String oldName, String newName) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void updateAllergeni(Category category) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void delete(String name) throws DaoException {
		// TODO Auto-generated method stub

	}

}
