package org.gmnz.vega.base;


import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.repository.CategoriaDao;
import org.gmnz.vega.repository.CategoriaHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class CategoriaHbnDaoTest extends BaseHbnDaoTest {

	private CategoriaDao dao;

	static final String CAT_W_ALLERGENI_NAME = "CAT_W_ALLERGENI_NAME";



	@Before
	public void before() {
		dao = new CategoriaHbnDao();

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

	/*
	private static final String CARNI_TEST = "CarniTest";
	private static final String CEREALI_TEST = "CategoriaCerealiTest";
	private static final String TEST_CATEGORIA_NOME = "testCategoria";
	private static final String TEST_CATEGORIA_NOME_UPD = "testCategoriaUPDATED";



	@Test
	public void findAllTest() throws DaoException {
		CategoriaDao dao = new CategoriaHbnDao();
		List<Categoria> categorie = dao.findAll();
		Assert.assertTrue(categorie.size() >= 0);
	}



	@Test
	public void findByName() throws DaoException {
		final String categoryName = "DEFAULT_CATEGORY";
		CategoriaDao dao = new CategoriaHbnDao();

		Assert.assertNull(dao.findByName(null));
		Assert.assertNull(dao.findByName(""));
		Assert.assertNull(dao.findByName("non-existent-category"));

		Assert.assertNotNull(dao.findByName(categoryName));
	}



	@Test
	public void create() throws DaoException {
		CategoriaDao dao = new CategoriaHbnDao();
		{
			Categoria c = new Categoria("DEFAULT_CATEGORY");
			boolean expectedExceptionThrown = false;
			try {
				dao.create(c);
			} catch (DaoException e) {
				expectedExceptionThrown = true;
			} finally {
				Assert.assertTrue(expectedExceptionThrown);
			}
		}

		Categoria c = new Categoria("categoriaTest");
		dao.create(c);
		Assert.assertNotNull(dao.findByName("categoriaTest"));
		// TODO eliminare la categoria di test appena creata, per lasciare il db in uno stato consistente

		Categoria c2 = new Categoria("c2");
		Allergene c2_a1 = new Allergene("c2_a1");
		Allergene c2_a2 = new Allergene("c2_a2");
		c2.add(c2_a1);
		c2.add(c2_a2);
		c2_a1.setCategoria(c2);
		c2_a2.setCategoria(c2);

		dao.create(c2);

		Categoria c2Actual = dao.findByName("c2");
		Assert.assertNotNull(c2Actual);
		Assert.assertEquals(2, c2Actual.getAllergeni().size());

		// TODO pulizia
	}
/*



	@Test
	public void addAllergeneTest() {
		Categoria carni = new Categoria(CARNI_TEST);
		Allergene manzo = new Allergene("ManzoTest");
		Allergene maiale = new Allergene("MaialeTest");

		AllergeneDao allergeneDao = new AllergeneHbnDao();
		CategoriaDao categoriaDao = new CategoriaHbnDao();

		allergeneDao.create(Arrays.asList(manzo, maiale));

		carni.add(manzo);
		carni.add(maiale);

		categoriaDao.create(carni);

		categoriaDao.delete(CARNI_TEST);
		allergeneDao.delete("ManzoTest");
		allergeneDao.delete("MaialeTest");
	}



	@Test
	public void changeAllergeneList() {
		Categoria testCategoria = new Categoria("testCategoria");
		Allergene a1 = new Allergene("a_uno");
		Allergene a2 = new Allergene("a_due");
		Allergene a3 = new Allergene("a_tre");
		Allergene a4 = new Allergene("a_quattro");

		testCategoria.add(a1);
		testCategoria.add(a2);

		a1.setCategoria(testCategoria);
		a2.setCategoria(testCategoria);

		CategoriaDao categoriaDao = new CategoriaHbnDao();
		categoriaDao.create(testCategoria);

		AllergeneDao allergeneDao = new AllergeneHbnDao();
		allergeneDao.create(Arrays.asList(a1, a2, a3, a4));


		testCategoria.remove(a1);
		testCategoria.remove(a2);

		testCategoria.add(a3);
		testCategoria.add(a4);

		categoriaDao.update(testCategoria);

		Categoria actual = categoriaDao.findByName("testCategoria");
		Assert.assertEquals(2, actual.getAllergeni().size());

		Assert.assertTrue(actual.getAllergeni().contains(a3));
		Assert.assertTrue(actual.getAllergeni().contains(a4));

		categoriaDao.delete("testCategoria");
		allergeneDao.delete("a_uno");
		allergeneDao.delete("a_due");
		allergeneDao.delete("a_tre");
		allergeneDao.delete("a_quattro");
	}



	@Test
	public void updateTest() {
		Categoria testCategoria = new Categoria(TEST_CATEGORIA_NOME);
		CategoriaDao dao = new CategoriaHbnDao();
		dao.create(testCategoria);

		dao.update(TEST_CATEGORIA_NOME, TEST_CATEGORIA_NOME_UPD);
		Assert.assertNull(dao.findByName(TEST_CATEGORIA_NOME));
		Assert.assertNotNull(dao.findByName(TEST_CATEGORIA_NOME_UPD));

		dao.delete(TEST_CATEGORIA_NOME_UPD);
	}



	@Test
	public void deletionSimpleTest() {
		Categoria catCerealiTest = new Categoria(CEREALI_TEST);
		CategoriaDao dao = new CategoriaHbnDao();
		dao.create(catCerealiTest);
		Assert.assertEquals(catCerealiTest, dao.findByName(CEREALI_TEST));
		dao.delete(CEREALI_TEST);
		Assert.assertNull(dao.findByName(CEREALI_TEST));
	}



	@Test
	public void deletionWithNonEmptyListTest() {
		Categoria catCerealiTest = new Categoria(CEREALI_TEST);
		CategoriaDao dao = new CategoriaHbnDao();
		Allergene a1 = new Allergene("a_uno");
		Allergene a2 = new Allergene("a_due");

		catCerealiTest.add(a1);
		catCerealiTest.add(a2);

		AllergeneDao allergeneDao = new AllergeneHbnDao();

		allergeneDao.create(Arrays.asList(new Allergene[]{a1, a2}));

		dao.create(catCerealiTest);

		dao.delete(CEREALI_TEST);
	}

*/

/*

	@Test
	public void bulkCreateTest() {
		Categoria farina = new Categoria(CEREALI_TEST);
		Categoria orzo = new Categoria(ORZO);
		Categoria patate = new Categoria(PATATE);

		CategoriaDao dao = new CategoriaHbnDao();

		dao.create(Arrays.asList(farina, orzo, patate));

		final List<Categoria> result = dao.findByPattern("%Test");
		Assert.assertEquals(3, result.size());

		for (Categoria a : result) {
			dao.delete(a.getNome());
		}

	}
*/

}
