package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.*;
import org.junit.*;

import java.util.List;


public class CategoryHbnDaoTest extends BaseHbnDaoTest {

	private CategoriaDao dao;

	static final String CAT_W_ALLERGENI_NAME = "CAT_W_ALLERGENI_NAME";
	private static final String CATEGORY_TEST_NAME = "CATEGORY_TEST_NAME";
	private static final String CATEGORY_TEST_NAME2 = "CATEGORY_TEST_NAME2";
	private static final String ALLERGENE_TEST_NAME = "CategoryHbnDaoTest.AllergenTest";



	@Before
	public void before() {
		dao = new CategoriaHbnDao();
	}



	@After
	public  void after() throws DaoException {
		CategoriaDao dao = new CategoriaHbnDao();
		Category byName = dao.findByName(CATEGORY_TEST_NAME);
		if (byName != null) {
			dao.delete(CATEGORY_TEST_NAME);
		}
		byName = dao.findByName(CATEGORY_TEST_NAME2);
		if (byName != null) {
			dao.delete(CATEGORY_TEST_NAME2);
		}

		AllergeneDao allergeneDao = new AllergeneHbnDao();
		allergeneDao.delete(ALLERGENE_TEST_NAME);

	}



	@Test
	public void findAll() throws DaoException {
		final List<Category> categoryList = dao.findAll();
		Assert.assertTrue(categoryList.size() >= 1);
	}



	@Test
	public void findByName() throws DaoException {
		Category expected = null;
		Category actual = dao.findByName(null);
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
		Category expected = new Category(CATEGORY_TEST_NAME);
		dao.create(CATEGORY_TEST_NAME);
		Category actual = dao.findByName(CATEGORY_TEST_NAME);
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
			dao.create(Category.DEFAULT_CATEGORY_NAME);
		} catch (DaoException e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage().contains("already exists"));
			throw e;
		}
	}



	@Test
	public void createCategoryPlain() throws DaoException {
		Category expected = new Category(CATEGORY_TEST_NAME2);
		dao.create(CATEGORY_TEST_NAME2);
		Category actual = dao.findByName(CATEGORY_TEST_NAME2);
		Assert.assertEquals(actual.getAllergens().size(), 0);

	}



	@Test
	public void createCategoryWithAllergeneList() throws DaoException {
		Category c = new Category(CATEGORY_TEST_NAME);
		Allergen a = new Allergen(ALLERGENE_TEST_NAME);
		a.setCategory(c);
		c.add(a);
		dao.create(CATEGORY_TEST_NAME);
		AllergeneDao allergeneDao = new AllergeneHbnDao();
		allergeneDao.create(a);

		Category actual = dao.findByName(CATEGORY_TEST_NAME);
		System.out.println(actual);
	}

}
