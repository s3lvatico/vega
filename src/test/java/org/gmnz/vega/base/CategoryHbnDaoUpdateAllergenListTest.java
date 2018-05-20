package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.CategoriaDao;
import org.gmnz.vega.repository.CategoriaHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CategoryHbnDaoUpdateAllergenListTest extends BaseHbnDaoTest {

	private static final String TEST_NAME = CategoryHbnDaoUpdateAllergenListTest.class.getSimpleName() + "_TEST_NAME";

	private CategoriaDao dao;



	@Before
	public void before() throws DaoException {
		dao = new CategoriaHbnDao();
		Category workCategory = new Category(TEST_NAME);
		Allergen a = new Allergen("a");
		Allergen b = new Allergen("b");
		Allergen c = new Allergen("c");

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
