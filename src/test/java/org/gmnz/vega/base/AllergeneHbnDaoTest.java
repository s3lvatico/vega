package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class AllergeneHbnDaoTest extends BaseHbnDaoTest {

	static final String AVENA = "AvenaTest";
	static final String FARINA = "FarinaTest";
	static final String ORZO = "OrzoTest";
	static final String PATATE = "PatateTest";



	@Test
	public void findAllTest() throws DaoException {
		AllergeneDao dao = new AllergeneHbnDao();
		List<Allergene> allergeni = dao.findAll();
		for (Allergene a : allergeni) {
			System.out.println(a);
		}
	}



	@Test(expected = DaoException.class)
	public void createWithNull() throws DaoException {
		AllergeneDao dao = new AllergeneHbnDao();
		Allergene nullValuedBusinessObject = null;
		dao.create(nullValuedBusinessObject);
	}



	@Test(expected = DaoException.class)
	public void createWithNullNamed() throws DaoException {
		AllergeneDao dao = new AllergeneHbnDao();
		String nullString = null;
		Allergene bo = new Allergene(nullString);
		dao.create(bo);
	}



	@Test(expected = DaoException.class)
	public void createWithEmptyNamed() throws DaoException {
		AllergeneDao dao = new AllergeneHbnDao();
		Allergene bo = new Allergene("");
		dao.create(bo);
	}



	@Test
	public void createAndReadTest() throws DaoException {
		Allergene avena = new Allergene(AVENA);

		AllergeneDao dao = new AllergeneHbnDao();


		dao.create(avena);

		Assert.assertEquals(avena, dao.findByName(AVENA));

		dao.delete(AVENA);
	}



	@Test
	public void deletionTest() throws DaoException {
		Allergene farina = new Allergene(FARINA);
		AllergeneDao dao = new AllergeneHbnDao();
		dao.create(farina);
		Assert.assertEquals(farina, dao.findByName(FARINA));
		dao.delete(FARINA);
		Assert.assertNull(dao.findByName(FARINA));
	}



	@Test
	public void updateTest() throws DaoException {
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
	public void bulkCreateTest() throws DaoException {
		Allergene farina = new Allergene(FARINA);
		Allergene orzo = new Allergene(ORZO);
		Allergene patate = new Allergene(PATATE);

		AllergeneDao dao = new AllergeneHbnDao();

		dao.create(Arrays.asList(farina, orzo, patate));

		// TODO rivedere!


//		final List<Allergene> result = dao.findByPattern("%Test");
//		Assert.assertEquals(3, result.size());
//
//		for (Allergene a : result) {
//			dao.delete(a.getNome());
//		}

	}

}
