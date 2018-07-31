package org.gmnz.vega.repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	public boolean isCategoryRegistered(String name) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT COUNT(*) FROM category WHERE name = ? AND deleted = '0'");
			ps.setString(1, name);
			rs = ps.executeQuery();
			rs.next(); // deve esser fatto per forza
			int countValue = rs.getInt(1);
			return countValue == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.isCategoryRegistered error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



	@Override
	public Category findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void create(String name) throws DaoException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("INSERT INTO category VALUES(?, ?, '0')");
			s.setString(1, UUID.randomUUID().toString());
			s.setString(2, name);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.create error", e);
		} finally {
			releaseResources(s);
		}
	}



	@Override
	public void update(Category category) throws DaoException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("UPDATE category SET e_name = ? WHERE id = ?;");
			s.setString(1, category.getName());
			s.setString(2, category.getId());
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
