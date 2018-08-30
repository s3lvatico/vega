package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao {

	protected CategoryDaoImpl(DataSource dataSource, PlatformTransactionManager transactionManager) {
		super(dataSource, transactionManager);
	}



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
	public Category findById(String id) throws DaoException {
		String sqlQuery = "SELECT * FROM category WHERE id = ? AND deleted = 0";
		try {
			return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new CategoryRowMapper());
		} catch (EmptyResultDataAccessException e) {
			System.err.println("warning: no category for id <" + id + ">");
			return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DaoException(getClass().getSimpleName() + ".findById caught exception", e);
		}
	}



	@Override
	public Category findByName(String name, boolean deleted) throws DaoException {
		String sqlQuery = "SELECT * FROM category WHERE e_name = ? AND deleted = ?";
		try {
			return jdbcTemplate.queryForObject(sqlQuery, new Object[]{name, deleted ? 1 : 0}, new CategoryRowMapper());
		} catch (EmptyResultDataAccessException e) {
			System.err.println("warning: no results");
			return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DaoException(getClass().getSimpleName() + ".findByName caught exception", e);
		}
	}



	@Override
	public boolean isCategoryRegisteredByName(String name) throws DaoException {
		String sqlQuery = "SELECT COUNT(*) FROM category WHERE e_name = ? AND deleted = '0'";
		int foundCategoriesWithName = jdbcTemplate.queryForObject(sqlQuery, Integer.class, name);
		if (foundCategoriesWithName > 1) {
			throw new DaoException("wrong number of categories found with name <" + name + ">");
		} else {
			return foundCategoriesWithName == 1;
		}
	}



	@Override
	public boolean isCategoryRegisteredById(String categoryId) {
		String sqlQuery = "SELECT COUNT(*) FROM category WHERE id = ? AND deleted = '0'";
		int foundCategoriesWithId = jdbcTemplate.queryForObject(sqlQuery, Integer.class, categoryId);
		return foundCategoriesWithId == 1;
	}



	@Override
	public String create(String name) {
		String newId = UUID.randomUUID().toString();
		jdbcTemplate.update("INSERT INTO category VALUES(?, ?, '0')", newId, name);
		return newId;
	}



	@Override
	public void update(Category category) {
		jdbcTemplate.update("UPDATE category SET e_name = ? WHERE id = ?", category.getName(), category.getId());
	}



	@Override
	public int countAllergens(String categoryId) {
		String sqlQuery = "SELECT COUNT(*) FROM allergen WHERE id_category = ?";
		return jdbcTemplate.queryForObject(sqlQuery, Integer.class, categoryId);
	}



	@Override
	public void delete(String categoryId) throws DaoException {
		String sqlStatement = "UPDATE category SET deleted = 1 WHERE id = ?";
		jdbcTemplate.update(sqlStatement, categoryId);
	}



	@Override
	public void deepDelete(String categoryId) throws DaoException {
		String sqlStatement = "DELETE FROM category WHERE id = ?";
		jdbcTemplate.update(sqlStatement, categoryId);
	}
}
