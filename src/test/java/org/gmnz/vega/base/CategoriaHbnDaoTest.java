package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.gmnz.vega.repository.CategoriaDao;
import org.gmnz.vega.repository.CategoriaHbnDao;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class CategoriaHbnDaoTest {

	static final String CARNI_TEST = "CarniTest";
	static final String CEREALI_TEST = "CerealiTest";

	private static HibernateUtil hibernateUtil;



	@BeforeClass
	public static void boostrapHibernateSystem() {
		hibernateUtil = new HibernateUtil();
		hibernateUtil.bootstrap();
	}



	@AfterClass
	public static void shutdownHibernateSystem() {
		hibernateUtil.shutdown();
	}



	@Test
	public void findAllTest() {
		CategoriaDao dao = new CategoriaHbnDao();
		List<Categoria> categorie = dao.findAll();
		for (Categoria categoria : categorie) {
			System.out.println(categoria);
		}
	}



	@Test
	public void createAndReadTest() {
		Categoria carni = new Categoria(CARNI_TEST);
		CategoriaDao dao = new CategoriaHbnDao();
		dao.create(carni);
		Categoria actual = dao.findByName(CARNI_TEST);
		Assert.assertEquals(carni, actual);
		dao.delete(CARNI_TEST);
	}



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

		AllergeneDao allergeneDao = new AllergeneHbnDao();
		allergeneDao.create(Arrays.asList(a1, a2, a3, a4));

		CategoriaDao categoriaDao = new CategoriaHbnDao();
		categoriaDao.create(testCategoria);

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


/*
	@Test
	public void deletionTest() {
		Categoria farina = new Categoria(CEREALI_TEST);
		CategoriaDao dao = new CategoriaHbnDao();
		dao.create(farina);
		Assert.assertEquals(farina, dao.findByName(CEREALI_TEST));
		dao.delete(CEREALI_TEST);
		Assert.assertNull(dao.findByName(CEREALI_TEST));
	}
*/



/*	@Test
	public void updateTest() {
		final String ORZO_MODIFICATO = "orzoModificato";

		Categoria orzo = new Categoria(ORZO);
		CategoriaDao dao = new CategoriaHbnDao();

		// per consistenza nella gestione di eventuali errori precedenti
		dao.delete(ORZO_MODIFICATO);

		dao.create(orzo);

		dao.update(ORZO, ORZO_MODIFICATO);
		Assert.assertNull(dao.findByName(ORZO));
		Assert.assertNotNull(dao.findByName(ORZO_MODIFICATO));

		dao.delete(ORZO_MODIFICATO);
	}*/


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
