package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
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

// 		Statement s = null;
//		ResultSet rs = null;
//		try {
//			s = connection.createStatement();
//			rs = s.executeQuery("SELECT allrg.id, allrg.e_name allergen_name, cat.e_name category_name \n"
//					+ " FROM category cat\n" + " JOIN allergen allrg ON allrg.id_category = cat.id\n"
//					+ " WHERE allrg.deleted = 0" + " ORDER BY category_name");
//			ArrayList<Allergen> allergens = new ArrayList<>();
//			while (rs.next()) {
//				String id = rs.getString(1);
//				String allergenName = rs.getString("allergen_name");
//				Allergen a = new Allergen(allergenName);
//				a.setId(id);
//				String categoryName = rs.getString("category_name");
//				a.setCategory(new Category(categoryName));
//				allergens.add(a);
//			}
//			return allergens;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("AllergenDaoImpl.findAll error", e);
//		} finally {
//			releaseResources(s, rs);
//		}
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
		return jdbcTemplate.queryForObject(sqlQuery, new Object[] {id}, new AllergenByIdRowMapper());
//		PreparedStatement s = null;
//		ResultSet rs = null;
//		try {
//			s = connection.prepareStatement("select " + " allergen.id allergen_id, " + " allergen.e_name allergen_name, "
//					+ " category.id category_id, " + " category.e_name category_name " + " from allergen "
//					+ " join category on " + " allergen.id_category = category.id and allergen.deleted = 0 "
//					+ " where  allergen.id = ?");
//			s.setString(1, id);
//			rs = s.executeQuery();
//
//			Allergen a = null;
//			if (rs.next()) {
//				a = new Allergen(rs.getString("allergen_name"));
//				a.setId(rs.getString("allergen_id"));
//				Category c = new Category(rs.getString("category_name"));
//				c.setId(rs.getString("category_id"));
//				a.setCategory(c);
//			}
//			return a;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("AllergenDaoImpl.findById error", e);
//		} finally {
//			releaseResources(s, rs);
//		}
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

//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			ps = connection.prepareStatement("SELECT COUNT(*) FROM allergen WHERE e_name = ? AND deleted = '0'");
//			ps.setString(1, name);
//			rs = ps.executeQuery();
//			rs.next(); // deve esser fatto per forza
//			int countValue = rs.getInt(1);
//			return countValue == 1;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("AllergenDaoImpl.isAllergenRegisteredByName error", e);
//		} finally {
//			releaseResources(ps, rs);
//		}
	}



	@Override
	public void create(Allergen allergen) throws DaoException {
		String sqlStatement = "INSERT  INTO  allergen VALUES (?, ?, 0, ?)";
		Object[] params = {UUID.randomUUID().toString(), allergen.getName(), allergen.getCategory().getId()};
		jdbcTemplate.update(sqlStatement, params);
//		PreparedStatement s = null;
//		try {
//			s = connection.prepareStatement("INSERT  INTO  allergen VALUES (?, ?, 0, ?)");
//			s.setString(1, UUID.randomUUID().toString());
//			s.setString(2, allergen.getName());
//			s.setString(3, allergen.getCategory().getId());
//			s.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("AllergenDaoImpl.create error", e);
//		} finally {
//			releaseResources(s);
//		}
	}



	@Override
	public void update(Allergen allergen) throws DaoException {
		String sqlStatement = "update allergen set e_name = ?, id_category = ? where id = ? and deleted = 0";
		Object[] updateParams = {allergen.getName(), allergen.getCategory().getId(), allergen.getId()};
		jdbcTemplate.update(sqlStatement, updateParams);
//		PreparedStatement s = null;
//		try {
//			s = connection
//					.prepareStatement("update allergen set e_name = ?, id_category = ? where id = ? and deleted = 0");
//			s.setString(1, allergen.getName());
//			s.setString(2, allergen.getCategory().getId());
//			s.setString(3, allergen.getId());
//			s.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("AllergenDaoImpl.update error", e);
//		} finally {
//			releaseResources(s);
//		}
	}



	@Override
	public void delete(String id) throws DaoException {
		String sqlStatement = "update allergen set deleted = 1 where id = ?";
		jdbcTemplate.update(sqlStatement, id);
//		PreparedStatement s = null;
//		try {
//			s = connection.prepareStatement("update allergen set deleted = 1 where id = ?");
//			s.setString(1, id);
//			s.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("AllergenDaoImpl.delete error", e);
//		} finally {
//			releaseResources(s);
//		}
	}

}
