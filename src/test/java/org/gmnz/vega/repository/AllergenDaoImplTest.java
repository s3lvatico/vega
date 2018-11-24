package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;


/**
 * creato da simone in data 24/11/2018.
 */
public class AllergenDaoImplTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	Statement s;
	@Mock
	ResultSet rs;
	@Mock
	Connection c;

	private String sampleId = UUID.randomUUID().toString();
	private String allergenName = "un certo allergene";
	private String categoryName = "una certa categoria";



	@Before
	public void setup() throws SQLException {

		when(c.createStatement()).thenReturn(s);
		when(s.executeQuery(Mockito.anyString())).thenReturn(rs);

		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getString(Mockito.anyInt())).thenReturn(sampleId);
		when(rs.getString(Mockito.anyString())).thenReturn(allergenName).thenReturn(categoryName);

		// when(s.close()).thenReturn(null);
	}



	@Test
	public void findAll() throws DaoException {
		AllergenDaoImpl dao = new AllergenDaoImpl();
		dao.connection = c;
		List<Allergen> findAll = dao.findAll();

		Assert.assertEquals(1, findAll.size());

		Allergen target = findAll.get(0);
		Assert.assertEquals(allergenName, target.getName());
		Assert.assertEquals(categoryName, target.getCategory().getName());
	}

	@Test(expected = DaoException.class)
	public void testDaoException() throws SQLException, DaoException {

		when(c.createStatement()).thenThrow(SQLException.class);

		AllergenDaoImpl dao = new AllergenDaoImpl();
		dao.connection = c;
		dao.findAll();

	}

}
