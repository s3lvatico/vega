package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class AllergeneHbnDaoTest {

	public static final String AVENA = "AvenaTest";
	public static final String FARINA = "FarinaTest";
	public static final String ORZO = "OrzoTest";
	public static final String PATATE = "PatateTest";

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
		AllergeneDao dao = new AllergeneHbnDao();
		List<Allergene> allergeni = dao.findAll();
		for (Allergene a : allergeni) {
			System.out.println(a);
		}
	}



	@Test
	public void createAndReadTest() {
		Allergene avena = new Allergene(AVENA);

		AllergeneDao dao = new AllergeneHbnDao();


		dao.create(avena);

		Assert.assertEquals(avena, dao.findByName(AVENA));

		dao.delete(AVENA);
	}



	@Test
	public void deletionTest() {
		Allergene farina = new Allergene(FARINA);
		AllergeneDao dao = new AllergeneHbnDao();
		dao.create(farina);
		Assert.assertEquals(farina, dao.findByName(FARINA));
		dao.delete(FARINA);
		Assert.assertNull(dao.findByName(FARINA));
	}



	@Test
	public void updateTest() {
		final String ORZO_MODIFICATO = "orzoModificato";

		Allergene orzo = new Allergene(ORZO);
		AllergeneDao dao = new AllergeneHbnDao();

		// per consistenza nella gestione di eventuali errori precedenti
		dao.delete(ORZO_MODIFICATO);

		dao.create(orzo);

		dao.update(ORZO, ORZO_MODIFICATO);
		Assert.assertNull(dao.findByName(ORZO));
		Assert.assertNotNull(dao.findByName(ORZO_MODIFICATO));

		dao.delete(ORZO_MODIFICATO);
	}



	@Test
	public void bulkCreateTest() {
		Allergene farina = new Allergene(FARINA);
		Allergene orzo = new Allergene(ORZO);
		Allergene patate = new Allergene(PATATE);

		AllergeneDao dao = new AllergeneHbnDao();

		dao.create(Arrays.asList(farina, orzo, patate));

		final List<Allergene> result = dao.findByPattern("%Test");
		Assert.assertEquals(3, result.size());

		for (Allergene a : result) {
			dao.delete(a.getNome());
		}

	}

}
