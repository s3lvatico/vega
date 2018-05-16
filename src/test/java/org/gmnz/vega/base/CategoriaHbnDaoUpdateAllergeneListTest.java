package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.repository.CategoriaDao;
import org.gmnz.vega.repository.CategoriaHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CategoriaHbnDaoUpdateAllergeneListTest extends BaseHbnDaoTest {

	private static final String TEST_NAME = CategoriaHbnDaoUpdateAllergeneListTest.class.getSimpleName() + "_TEST_NAME";

	private CategoriaDao dao;



	@Before
	public void before() throws DaoException {
		dao = new CategoriaHbnDao();
		Categoria workCategory = new Categoria(TEST_NAME);
		Allergene a = new Allergene("a");
		Allergene b = new Allergene("b");
		Allergene c = new Allergene("c");

		workCategory.add(a);
		workCategory.add(b);
		workCategory.add(c);


		dao.create(TEST_NAME);
	}



	@After
	public void after() throws DaoException {
		dao.delete(TEST_NAME);
	}



	@Test
	public void plainUpdateAllergeneList() {
	}


}
