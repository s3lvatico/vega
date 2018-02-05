package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AllergeneHbnDaoCreateExistingTest extends BaseHbnDaoTest {

	AllergeneDao dao;

	final String TEST_ALLERGENE_NAME = "Test Allergene";



	@Before
	public void before() throws DaoException {
		Allergene basicBo = new Allergene(TEST_ALLERGENE_NAME);
		dao = new AllergeneHbnDao();
		dao.create(basicBo);
	}



	@Test(expected = DaoException.class)
	public void createNewWithExistingName() throws DaoException {
		Allergene duplicateBo = new Allergene(TEST_ALLERGENE_NAME);
		dao.create(duplicateBo);
	}



	@After
	public void after() throws DaoException {
		dao.delete(TEST_ALLERGENE_NAME);
	}


}
