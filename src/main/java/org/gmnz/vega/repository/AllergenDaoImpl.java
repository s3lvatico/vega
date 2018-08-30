package org.gmnz.vega.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


class AllergenDaoImpl extends BasicDaoImpl implements AllergenDao {

	protected AllergenDaoImpl(DataSource dataSource, PlatformTransactionManager transactionManager) {
		super(dataSource, transactionManager);
	}



	static final class AllergenRowMapper implements RowMapper<Allergen> {

		@Override
		public Allergen mapRow(ResultSet resultSet, int i) throws SQLException {
			String id = resultSet.getString(1);
			String allergenName = resultSet.getString("allergen_name");
			Allergen a = new Allergen(allergenName);
			a.setId(id);
			String categoryName = resultSet.getString("category_name");
			a.setCategory(new Category(categoryName));
			return a;
		}
	}



	@Override
	public List<Allergen> findAll() throws DaoException {
//@formatter:off
		String sqlQuery =
				  " SELECT allrg.id, allrg.e_name allergen_name, cat.e_name category_name "
				+ " FROM category cat "
				+ " JOIN allergen allrg ON allrg.id_category = cat.id "
				+ " WHERE allrg.deleted = 0 "
				+ " ORDER BY category_name, allergen_name ";
//@formatter:on
		return jdbcTemplate.query(sqlQuery, new AllergenRowMapper());
	}

	static class AllergenByIdRowMapper implements RowMapper<Allergen> {

		@Override
		public Allergen mapRow(ResultSet resultSet, int i) throws SQLException {
			Allergen a;
			a = new Allergen(resultSet.getString("allergen_name"));
			a.setId(resultSet.getString("allergen_id"));
			Category c = new Category(resultSet.getString("category_name"));
			c.setId(resultSet.getString("category_id"));
			a.setCategory(c);
			return a;
		}
	}



	@Override
	public Allergen findById(String id) throws DaoException {
//@formatter:off
		String sqlQuery =
				"SELECT  " +
						"allergen.id allergen_id, " +
						"allergen.e_name allergen_name, " +
						"category.id category_id, " +
						"category.e_name category_name " +
						"FROM allergen " +
						"JOIN category ON allergen.id_category = category.id AND allergen.deleted = 0 " +
						"WHERE allergen.id = ? ";
//@formatter:on
		try {
			return jdbcTemplate.queryForObject(sqlQuery, new Object[] { id }, new AllergenByIdRowMapper());
		} catch (EmptyResultDataAccessException e) {
			System.err.println("warning: no allergen for id <" + id + ">");
			return null;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new DaoException(getClass().getSimpleName() + ".findById caught exception", e);
		}
	}



	@Override
	public boolean isAllergenRegisteredByName(String name) throws DaoException {
		String sqlStatement = "SELECT COUNT(*) FROM allergen WHERE e_name = ? AND deleted = '0'";
		int count = jdbcTemplate.queryForObject(sqlStatement, new Object[] { name }, Integer.class);
		if (count > 1) {
			throw new DaoException("wrong number of allergens found with name <" + name + ">");
		} else {
			return count == 1;
		}
	}



	@Override
	public String create(Allergen allergen) throws DaoException {
		String sqlStatement = "INSERT  INTO  allergen VALUES (?, ?, 0, ?)";
		String allergenId = UUID.randomUUID().toString();
		Object[] params = { allergenId, allergen.getName(), allergen.getCategory().getId() };
		jdbcTemplate.update(sqlStatement, params);
		return allergenId;
	}



	@Override
	public void update(Allergen allergen) throws DaoException {
		String sqlStatement = "UPDATE allergen SET e_name = ?, id_category = ? WHERE id = ? AND deleted = 0";
		Object[] updateParams = { allergen.getName(), allergen.getCategory().getId(), allergen.getId() };
		jdbcTemplate.update(sqlStatement, updateParams);
	}



	@Override
	public void delete(String id) throws DaoException {
		String sqlStatement = "UPDATE allergen SET deleted = 1 WHERE id = ?";
		jdbcTemplate.update(sqlStatement, id);
	}

}
