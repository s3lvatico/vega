package org.gmnz.vega.base;

import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.junit.Test;

import java.util.List;

public class AllergeneHbnDaoTest {

	@Test
	public void bootstrapTest() {
		HibernateUtil hibernateUtil = new HibernateUtil();

		hibernateUtil.bootstrap();
		hibernateUtil.shutdown();
	}

	@Test public void findAllTest() {
		HibernateUtil u = new HibernateUtil();
		u.bootstrap();

		AllergeneDao dao = new AllergeneHbnDao();
		List<Allergene> allergeni = dao.findAll();
		for (Allergene a : allergeni) {
			System.out.println(a);
		}

		u.shutdown();
	}

	@Test public void createTest() {
		Allergene avena = new Allergene("Avena");
		Allergene farina = new Allergene("Farina");
		Allergene orzo = new Allergene("Orzo");
		Allergene patate = new Allergene("Patate");


		HibernateUtil u = new HibernateUtil();
		u.bootstrap();

		AllergeneDao dao = new AllergeneHbnDao();

		dao.create(avena); dao.create(farina); dao.create(orzo); dao.create(patate);

		u.shutdown();
	}
}
