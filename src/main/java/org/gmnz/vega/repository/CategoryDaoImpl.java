package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao {

// TODO usare il jdbcTemplate di Spring, al momento viene lanciata una NPE




	static final class CategoryRowMapper implements RowMapper<Category> {

		@Override
		public Category mapRow(ResultSet resultSet, int i) throws SQLException {
			Category c = new Category(resultSet.getString(2));
			c.setId(resultSet.getString(1));
			return c;
		}

	}



	@Override
	public List<Category> findAll() {
		return jdbcTemplate.query("SELECT * FROM category WHERE deleted = 0", new CategoryRowMapper());
	}



	@Override
	public List<Category> findAllWithAllergens() {
//@formatter:off
			String sqlQuery = "select  " +
					"category.id 		category_id, " +
					"category.e_name 	category_name, " +
					"allergen.id 		allergen_id,  " +
					"allergen.e_name 	allergen_name " +
					"from category  " +
					"	join allergen on  " +
					"		allergen.id_category = category.id  " +
					"        and allergen.deleted = 0 " +
					"        and category.deleted = 0 " +
					"order by category_name, allergen_name";
//@formatter:on

		return jdbcTemplate.query(sqlQuery, new CategoryWithAllergensRsExtractor());
	}



	static class CategoryWithAllergensRsExtractor implements ResultSetExtractor<List<Category>> {


		@Override
		public List<Category> extractData(ResultSet rs) throws SQLException {
			ArrayList<Category> categories = new ArrayList<>();

			Category currentCategory = new Category("dummyName");
			currentCategory.setId("dummyId");

			while (rs.next()) {
				String rsCategoryId = rs.getString("category_id");
				if (!currentCategory.getId().equals(rsCategoryId)) {
					currentCategory = new Category(rs.getString("category_name"));
					currentCategory.setId(rsCategoryId);
					categories.add(currentCategory);
				}
				Allergen a = new Allergen(rs.getString("allergen_name"));
				a.setId(rs.getString("allergen_id"));
				a.setCategory(currentCategory);
				currentCategory.addAllergen(a);
			}
			return categories;
		}
	}



	@Override
	public Category findById(String id) {
		return jdbcTemplate.queryForObject("SELECT * FROM category WHERE id = ? AND deleted = 0", new Object[]{id}, new CategoryRowMapper());
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
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("CategoryDaoImpl.countAllergens error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



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
