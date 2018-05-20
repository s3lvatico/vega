package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.repository.CategoriaDao;
import org.gmnz.vega.repository.CategoriaHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CategoryHbnDaoUpdateTest extends BaseHbnDaoTest {
/*
	nome categoria da aggiornare vuoto o nullo	nessuna operazione compiuta
	nome categoria non presente nel sistema	eccezione
	nuovo nome vuoto o nullo	nessuna operazione compiuta
	nuovo nome uguale a quello di una categoria già presente nel sistema	eccezione
*/

	private static final String TEST_NAME = CategoryHbnDaoUpdateTest.class.getSimpleName() + "_TEST_NAME";
	private static final String TEST_DUMMY_TARGET_NAME = CategoryHbnDaoUpdateTest.class.getSimpleName() + "_DUMMY_TARGET";

	private CategoriaDao dao;



	@Before
	public void before() throws DaoException {
		dao = new CategoriaHbnDao();
		dao.create(TEST_NAME);
		dao.create(TEST_DUMMY_TARGET_NAME);
	}



	@After
	public void after() throws DaoException {
		dao.delete(TEST_NAME);
		dao.delete(TEST_DUMMY_TARGET_NAME);
	}



	@Test
	public void updateNameWithInvalidSourceName() throws DaoException {
		final List<Category> expectedAll = dao.findAll();
		dao.updateRename(null, "some other name");
		List<Category> actualAll = dao.findAll();
		Assert.assertEquals(expectedAll, actualAll);
		dao.updateRename("", "some other name");
		actualAll = dao.findAll();
		Assert.assertEquals(expectedAll, actualAll);
	}



	@Test(expected = DaoException.class)
	public void updateNameWithNonExistingSourceName() throws DaoException {
		dao.updateRename("this category does not exist", "some other name");
	}



	@Test
	public void updateNameWithInvalidDestName() throws DaoException {
		final List<Category> expectedAll = dao.findAll();
		dao.updateRename(TEST_NAME, "");
		List<Category> actualAll = dao.findAll();
		Assert.assertEquals(expectedAll, actualAll);
		dao.updateRename(TEST_NAME, null);
		actualAll = dao.findAll();
		Assert.assertEquals(expectedAll, actualAll);
	}



	@Test(expected = DaoException.class)
	public void updateNameWithExistingDestinationName() throws DaoException {
		dao.updateRename(TEST_NAME, TEST_DUMMY_TARGET_NAME);
	}



	@Test(expected = DaoException.class)
	public void updateAllergeneListWithNullCategoryName() throws DaoException {
		Category dummyBo = new Category(null);
		dao.updateAllergeni(dummyBo);
	}



	@Test(expected = DaoException.class)
	public void updateAllergeneListWithEmptyCategoryName() throws DaoException {
		Category dummyBo = new Category("");
		dao.updateAllergeni(dummyBo);
	}



	@Test
	public void updateAllergeneListWithNonExistentCategoryName() throws DaoException {
		Category dummyBo = new Category("this-one-does-not-exist");
		try {
			dao.updateAllergeni(dummyBo);
		} catch (DaoException e) {
			Assert.assertTrue(e.getMessage().contains("does not exist"));
		}
	}



	@Test
	public void updateAllergeneListWithNonExistentAllergene() {
		Category dummyBo = new Category(TEST_NAME);
		Allergen nonExistentAllergen = new Allergen(Long.toString(System.currentTimeMillis()));
		dummyBo.add(nonExistentAllergen);
		try {
			dao.updateAllergeni(dummyBo);
		} catch (DaoException e) {
			Assert.assertTrue(e.getMessage().contains("not found in the system"));
		}
	}



	@Test
	public void plainUpdateAllergeneList() {
		Category dummyBo = new Category(TEST_NAME);
		Allergen nonExistentAllergen = new Allergen(Long.toString(System.currentTimeMillis()));
		dummyBo.add(nonExistentAllergen);
		try {
			dao.updateAllergeni(dummyBo);
		} catch (DaoException e) {
			Assert.assertTrue(e.getMessage().contains("not found in the system"));
		}
	}


}
