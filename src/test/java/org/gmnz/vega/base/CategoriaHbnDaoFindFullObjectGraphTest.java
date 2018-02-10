package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.repository.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CategoriaHbnDaoFindFullObjectGraphTest extends BaseHbnDaoTest {

	private CategoriaDao categoriaDao;
	private AllergeneDao allergeneDao;

	static final String TEST_CATEGORY_NAME = "TEST_CATEGORY_NAME";

	static final String[] TEST_ALLERGENE_NOMI = {"test.allergene.1", "test.allergene.2", "test.allergene.3"};



	@Before
	public void before() throws DaoException {
		categoriaDao = new CategoriaHbnDao();
		Categoria testCategory = new Categoria(TEST_CATEGORY_NAME);
		categoriaDao.create(testCategory);

		allergeneDao = new AllergeneHbnDao();
		Allergene a;
		for (int i = 0; i < TEST_ALLERGENE_NOMI.length; i++) {
			a = new Allergene(TEST_ALLERGENE_NOMI[i]);
			a.setCategoria(testCategory);
			allergeneDao.create(a);
		}
	}



	@Test
	public void test() throws DaoException {
		Categoria actual = categoriaDao.findByName(TEST_CATEGORY_NAME);
		Assert.assertEquals(actual.getAllergeni().size(), TEST_ALLERGENE_NOMI.length);
	}



	@After
	public void after() throws DaoException {
		for (int i = 0; i < TEST_ALLERGENE_NOMI.length; i++) {
			allergeneDao.delete(TEST_ALLERGENE_NOMI[i]);
		}
		categoriaDao.delete(TEST_CATEGORY_NAME);
	}


}
