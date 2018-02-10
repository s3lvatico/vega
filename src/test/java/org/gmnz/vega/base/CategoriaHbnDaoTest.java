package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.repository.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CategoriaHbnDaoTest extends BaseHbnDaoTest {

	private CategoriaDao dao;

	static final String CAT_W_ALLERGENI_NAME = "CAT_W_ALLERGENI_NAME";
	private static final String CATEGORY_TEST_NAME = "CATEGORY_TEST_NAME";
	private static final String CATEGORY_TEST_NAME2 = "CATEGORY_TEST_NAME2";



	@Before
	public void before() {
		dao = new CategoriaHbnDao();
	}



	@AfterClass
	public static void afterClass() throws DaoException {
		CategoriaDao dao = new CategoriaHbnDao();
		Categoria byName = dao.findByName(CATEGORY_TEST_NAME);
		if (byName != null) {
			dao.delete(CATEGORY_TEST_NAME);
		}
		byName = dao.findByName(CATEGORY_TEST_NAME2);
		if (byName != null) {
			dao.delete(CATEGORY_TEST_NAME2);
		}
	}



	@Test
	public void findAll() throws DaoException {
		final List<Categoria> categoriaList = dao.findAll();
		Assert.assertTrue(categoriaList.size() >= 1);
	}



	@Test
	public void findByName() throws DaoException {
		Categoria expected = null;
		Categoria actual = dao.findByName(null);
		Assert.assertEquals(expected, actual);

		actual = dao.findByName("");
		Assert.assertEquals(expected, actual);
	}



	@Test
	public void findCategoryWrongName() throws DaoException {
		Assert.assertNull(dao.findByName("non-existent-category"));
	}



	@Test
	public void createNewCategory() throws DaoException {
		Categoria expected = new Categoria(CATEGORY_TEST_NAME);
		dao.create(CATEGORY_TEST_NAME);
		Categoria actual = dao.findByName(CATEGORY_TEST_NAME);
		Assert.assertEquals(expected, actual);
	}



	@Test(expected = DaoException.class)
	public void createCategoryNullName() throws DaoException {
		try {
			dao.create(null);
		} catch (DaoException e) {
			Assert.assertTrue(e.getMessage().contains("null or empty name specified"));
			throw e;
		}
	}



	@Test(expected = DaoException.class)
	public void createCategoryEmptyName() throws DaoException {
		try {
			dao.create("");
		} catch (DaoException e) {
			Assert.assertTrue(e.getMessage().contains("null or empty name specified"));
			throw e;
		}
	}



	@Test(expected = DaoException.class)
	public void createCategoryDuplicateName() throws DaoException {
		try {
			dao.create(Categoria.DEFAULT_CATEGORY_NAME);
		} catch (DaoException e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage().contains("already exists"));
			throw e;
		}
	}



	@Test
	public void createCategoryPlain() throws DaoException {
		Categoria expected = new Categoria(CATEGORY_TEST_NAME2);
		dao.create(CATEGORY_TEST_NAME2);
		Categoria actual = dao.findByName(CATEGORY_TEST_NAME2);
		Assert.assertEquals(actual.getAllergeni().size(), 0);

	}



	@Test
	public void createCategoryWithAllergeneList() throws DaoException {
		Categoria c = new Categoria(CATEGORY_TEST_NAME);
		Allergene a = new Allergene("Allergene A");
		a.setCategoria(c);
		c.add(a);
		dao.create(CATEGORY_TEST_NAME);
		AllergeneDao allergeneDao = new AllergeneHbnDao();
		allergeneDao.create(a);

		Categoria actual = dao.findByName(CATEGORY_TEST_NAME);
		System.out.println(actual);
	}

}
