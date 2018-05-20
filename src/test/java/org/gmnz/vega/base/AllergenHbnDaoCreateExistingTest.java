package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AllergenHbnDaoCreateExistingTest extends BaseHbnDaoTest {

	AllergeneDao dao;

	final String TEST_ALLERGENE_NAME = "Test Allergen";



	@Before
	public void before() throws DaoException {
		Allergen basicBo = new Allergen(TEST_ALLERGENE_NAME);
		dao = new AllergeneHbnDao();
		dao.create(basicBo);
	}



	@Test(expected = DaoException.class)
	public void createNewWithExistingName() throws DaoException {
		Allergen duplicateBo = new Allergen(TEST_ALLERGENE_NAME);
		dao.create(duplicateBo);
	}



	@After
	public void after() throws DaoException {
		dao.delete(TEST_ALLERGENE_NAME);
	}


}
