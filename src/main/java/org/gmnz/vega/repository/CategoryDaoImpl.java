package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
	public Category findById(String id) throws DaoException {
		PreparedStatement s = null;
		ResultSet rs = null;
		try {
			s = connection.prepareStatement("SELECT * FROM category WHERE id = ? AND deleted = 0");
			s.setString(1, id);
			rs = s.executeQuery();

			Category c = null;
			while (rs.next()) {
				c = new Category(rs.getString(2));
				c.setId(rs.getString(1));
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.findById error", e);
		} finally {
			releaseResources(s, rs);
		}
	}



	@Override
	public boolean isCategoryRegisteredByName(String name) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT COUNT(*) FROM category WHERE e_name = ? AND deleted = '0'");
			ps.setString(1, name);
			rs = ps.executeQuery();
			rs.next(); // deve esser fatto per forza
			int countValue = rs.getInt(1);
			return countValue == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.isCategoryRegisteredByName error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}

	@Override
	public boolean isCategoryRegisteredById(String categoryId) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT COUNT(*) FROM category WHERE id = ? AND deleted = '0'");
			ps.setString(1, categoryId);
			rs = ps.executeQuery();
			rs.next(); // deve esser fatto per forza
			int countValue = rs.getInt(1);
			return countValue == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.isCategoryRegisteredById error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



//	@Deprecated
//	@Override
//	public Category findByName(String name) throws DaoException {
//		//
//		return null;
//	}



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
			s = connection.prepareStatement("UPDATE category SET e_name = ? WHERE id = ?");
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



	@Override
	public int countAllergens(String categoryId) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT COUNT(*) FROM allergen WHERE id_category = ?");
			ps.setString(1, categoryId);
			rs = ps.executeQuery();
			rs.next(); // deve esser fatto per forza
			int countValue = rs.getInt(1);
			return countValue;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.countAllergens error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



//	@Deprecated
//	@Override
//	public void updateRename(String oldName, String newName) throws DaoException {
//		//
//
//	}


	@Override
	public void delete(String categoryId) throws DaoException {
		PreparedStatement s = null;
		try {
			s = connection.prepareStatement("DELETE  FROM category WHERE id = ?");
			s.setString(1, categoryId);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.delete error", e);
		} finally {
			releaseResources(s);
		}
	}

}
