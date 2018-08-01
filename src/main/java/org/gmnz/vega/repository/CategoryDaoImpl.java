package org.gmnz.vega.repository;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gmnz.vega.domain.Category;


// TODO referenziare il dummyrepository

class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() throws DaoException {
		return new ArrayList<>(DummyRepository.getRegisteredCategories());
	}



	@Override
	public boolean isCategoryRegistered(String name) throws DaoException {
		Collection<Category> registeredCategories = DummyRepository.getRegisteredCategories();
		Category c = new Category(name);
		return registeredCategories.contains(c);
	}



	@Override
	public Category findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void create(String name) throws DaoException {
// TODO CategoryDaoImpl.create controllo di esistenza da spostare sull'omologo serviceImpl
		Category c = new Category(name);
		DummyRepository.addCategory(c);
	}



	@Override
	public void update(Category category) throws DaoException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("UPDATE category SET e_name = ? WHERE id = ?;");
			s.setString(1, category.getName());
//			s.setString(2, category.getId());
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.update error", e);
		} finally {
			releaseResources(s);
		}
	}



	@Deprecated
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
