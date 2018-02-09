package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AllergeneHbnDaoCreateWithWrongCategoryData extends BaseHbnDaoTest {

	AllergeneDao dao;

	final String TEST_ALLERGENE_NAME_NO_CATEGORY = "createWithUnspecifiedCategory - Allergene";
	final String TEST_ALLERGENE_WRONG_CATEGORY = "createWithWrongCategory - Allergene";



	@Before
	public void before() throws DaoException {
		dao = new AllergeneHbnDao();
	}



	@Test
	public void createWithUnspecifiedCategory() throws DaoException {
		Allergene bo = new Allergene(TEST_ALLERGENE_NAME_NO_CATEGORY);
		bo.setCategoria(null);
		dao.create(bo);

		Allergene boActual = dao.findByName(TEST_ALLERGENE_NAME_NO_CATEGORY);
		Assert.assertEquals(Categoria.DEFAULT_CATEGORY_NAME, boActual.getCategoria().getNome());
	}



	@Test
	public void createWithWrongCategoryName() throws DaoException {
		Allergene bo = new Allergene(TEST_ALLERGENE_WRONG_CATEGORY);
		Categoria nonExistentCategory = new Categoria("non-esiste-nel-sistema");
		bo.setCategoria(nonExistentCategory);
		dao.create(bo);

		Allergene boActual = dao.findByName(TEST_ALLERGENE_WRONG_CATEGORY);
		Assert.assertEquals(Categoria.DEFAULT_CATEGORY_NAME, boActual.getCategoria().getNome());
	}



	@After
	public void after() throws DaoException {
		dao.delete(TEST_ALLERGENE_NAME_NO_CATEGORY);
		dao.delete(TEST_ALLERGENE_WRONG_CATEGORY);
	}


}
