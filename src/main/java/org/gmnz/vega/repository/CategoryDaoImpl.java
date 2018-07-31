package org.gmnz.vega.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.gmnz.vega.domain.Category;


class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() throws DaoException {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery("SELECT * FROM category WHERE deleted = 0");
			ArrayList<Category> categories = new ArrayList<>();
			while (rs.next()) {
				String id = rs.getString(1);
				String categoryName = rs.getString(2);
				Category c = new Category(categoryName);
				c.setId(id);
				categories.add(c);
			}
			return categories;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.findAll error", e);
		} finally {
			releaseResources(s, rs);
		}
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
