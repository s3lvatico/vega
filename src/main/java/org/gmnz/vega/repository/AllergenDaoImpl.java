package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class AllergenDaoImpl extends BasicDaoImpl implements AllergenDao {

	@Override
	public List<Allergen> findAll() throws DaoException {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery("SELECT allrg.id, allrg.e_name allergen_name, cat.e_name category_name \n" +
					" FROM category cat\n" +
					" JOIN allergen allrg ON allrg.id_category = cat.id\n" +
					" WHERE allrg.deleted = 0" +
					" ORDER BY category_name");
			ArrayList<Allergen> allergens = new ArrayList<>();
			while (rs.next()) {
				String id = rs.getString(1);
				String allergenName = rs.getString("allergen_name");
				Allergen a = new Allergen(allergenName);
				a.setId(id);
				String categoryName = rs.getString("category_name");
				a.setCategory(new Category(categoryName));
				allergens.add(a);
			}
			return allergens;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("AllergenDaoImpl.findAll error", e);
		} finally {
			releaseResources(s, rs);
		}
	}



	@Override
	public Allergen findByName(String name) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void create(Allergen allergen) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void create(Collection<Allergen> allergens) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void delete(String name) throws DaoException {
		// TODO Auto-generated method stub

	}



	@Override
	public void update(String oldName, String newName) throws DaoException {
		// TODO Auto-generated method stub

	}

}
