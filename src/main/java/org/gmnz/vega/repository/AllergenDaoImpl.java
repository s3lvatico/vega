package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


class AllergenDaoImpl extends BasicDaoImpl implements AllergenDao {

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
		String sqlQuery = 	"SELECT allrg.id, allrg.e_name allergen_name, cat.e_name category_name "
								+ " FROM category cat "
								+ " JOIN allergen allrg ON allrg.id_category = cat.id "
								+ " WHERE allrg.deleted = 0 "
								+ " ORDER BY category_name ";
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
//formatter:off
		String sqlQuery =
				"select  " +
						"allergen.id allergen_id, " +
						"allergen.e_name allergen_name, " +
						"category.id category_id, " +
						"category.e_name category_name " +
						"from allergen " +
						"join category on allergen.id_category = category.id and allergen.deleted = 0 " +
						"where allergen.id = ? ";
//formatter:on
		return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new AllergenByIdRowMapper());
	}



	@Override
	public boolean isAllergenRegisteredByName(String name) throws DaoException {
		String sqlStatement = "SELECT COUNT(*) FROM allergen WHERE e_name = ? AND deleted = '0'";
		int count = jdbcTemplate.queryForObject(sqlStatement, new Object[]{name}, Integer.class);
		if (count > 1) {
			throw new DaoException("wrong number of allergens found with name <" + name + ">");
		} else {
			return count == 1;
		}
	}



	@Override
	public void create(Allergen allergen) throws DaoException {
		String sqlStatement = "INSERT  INTO  allergen VALUES (?, ?, 0, ?)";
		Object[] params = {UUID.randomUUID().toString(), allergen.getName(), allergen.getCategory().getId()};
		jdbcTemplate.update(sqlStatement, params);
	}



	@Override
	public void update(Allergen allergen) throws DaoException {
		String sqlStatement = "update allergen set e_name = ?, id_category = ? where id = ? and deleted = 0";
		Object[] updateParams = {allergen.getName(), allergen.getCategory().getId(), allergen.getId()};
		jdbcTemplate.update(sqlStatement, updateParams);
	}



	@Override
	public void delete(String id) throws DaoException {
		String sqlStatement = "update allergen set deleted = 1 where id = ?";
		jdbcTemplate.update(sqlStatement, id);
	}

}
