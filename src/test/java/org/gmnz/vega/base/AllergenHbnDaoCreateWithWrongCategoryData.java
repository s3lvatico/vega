package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AllergenHbnDaoCreateWithWrongCategoryData extends BaseHbnDaoTest {

	AllergeneDao dao;

	final String TEST_ALLERGENE_NAME_NO_CATEGORY = "createWithUnspecifiedCategory - Allergen";
	final String TEST_ALLERGENE_WRONG_CATEGORY = "createWithWrongCategory - Allergen";



	@Before
	public void before() throws DaoException {
		dao = new AllergeneHbnDao();
	}



	@Test
	public void createWithUnspecifiedCategory() throws DaoException {
		Allergen bo = new Allergen(TEST_ALLERGENE_NAME_NO_CATEGORY);
		bo.setCategory(null);
		dao.create(bo);

		Allergen boActual = dao.findByName(TEST_ALLERGENE_NAME_NO_CATEGORY);
		Assert.assertEquals(Category.DEFAULT_CATEGORY_NAME, boActual.getCategory().getName());
	}



	@Test
	public void createWithWrongCategoryName() throws DaoException {
		Allergen bo = new Allergen(TEST_ALLERGENE_WRONG_CATEGORY);
		Category nonExistentCategory = new Category("non-esiste-nel-sistema");
		bo.setCategory(nonExistentCategory);
		dao.create(bo);

		Allergen boActual = dao.findByName(TEST_ALLERGENE_WRONG_CATEGORY);
		Assert.assertEquals(Category.DEFAULT_CATEGORY_NAME, boActual.getCategory().getName());
	}



	@After
	public void after() throws DaoException {
		dao.delete(TEST_ALLERGENE_NAME_NO_CATEGORY);
		dao.delete(TEST_ALLERGENE_WRONG_CATEGORY);
	}


}
