package org.gmnz.vega.base;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.repository.AllergeneDao;
import org.gmnz.vega.repository.AllergeneHbnDao;
import org.gmnz.vega.repository.DaoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class AllergeneHbnDaoTest extends BaseHbnDaoTest {

	static final String AVENA = "AvenaTest";
	static final String FARINA = "FarinaTest";
	static final String ORZO = "OrzoTest";
	static final String PATATE = "PatateTest";

	static final String SAMPLE_ALLERGENE_NAME = "SAMPLE_ALLERGENE_NAME";
	static final String ALLERGENE_2B_RENAMED = "ALLERGENE_2B_RENAMED";

	private AllergeneDao dao;



	@Before
	public void beforeTest() throws DaoException {
		dao = new AllergeneHbnDao();
		dao.create(new Allergene(SAMPLE_ALLERGENE_NAME));
		dao.create(new Allergene(ALLERGENE_2B_RENAMED));
	}



	@After
	public void afterTest() throws DaoException {
		dao.delete(SAMPLE_ALLERGENE_NAME);
		dao.delete(ALLERGENE_2B_RENAMED);
	}



	@Test
	public void findAllTest() throws DaoException {
		List<Allergene> allergeni = dao.findAll();
		for (Allergene a : allergeni) {
			System.out.println(a);
		}
	}



	@Test(expected = DaoException.class)
	public void createWithNull() throws DaoException {
		Allergene nullValuedBusinessObject = null;
		dao.create(nullValuedBusinessObject);
	}



	@Test(expected = DaoException.class)
	public void createWithNullNamed() throws DaoException {
		String nullString = null;
		Allergene bo = new Allergene(nullString);
		dao.create(bo);
	}



	@Test(expected = DaoException.class)
	public void createWithEmptyNamed() throws DaoException {
		Allergene bo = new Allergene("");
		dao.create(bo);
	}



	@Test
	public void getWithInvalidName() throws DaoException {
		Allergene actualFromNullName = dao.findByName(null);
		Assert.assertNull(actualFromNullName);

		Allergene actualFromEmptyName = dao.findByName("");
		Assert.assertNull(actualFromEmptyName);
	}



	@Test
	public void getWithValidName() throws DaoException {
		Allergene actual = dao.findByName(SAMPLE_ALLERGENE_NAME);
		Assert.assertNotNull(actual);
		Assert.assertEquals(actual.getNome(), SAMPLE_ALLERGENE_NAME);
		Assert.assertEquals(Categoria.DEFAULT_CATEGORY_NAME, actual.getCategoria().getNome());
		Assert.assertEquals(0, actual.getCategoria().getAllergeni().size());
	}



	@Test
	public void getNonExistentAllergene() throws DaoException {
		Allergene actual = dao.findByName("this name does not exist");
		Assert.assertNull(actual);
	}



	@Test(expected = DaoException.class)
	public void updateSrcNullName() throws DaoException {
		dao.update(null, "qualche altro nome");
	}



	@Test(expected = DaoException.class)
	public void updateSrcEmptyName() throws DaoException {
		dao.update("", "qualche altro nome");
	}



	@Test
	public void updateSrcNotFound() {
		try {
			dao.update("allergene inesistente", "qualche altro nome");
		} catch (DaoException e) {
			Assert.assertTrue(e.getMessage().contains("Can't rename non existent entity"));
		}
	}



	@Test(expected = DaoException.class)
	public void updateDstNullName() throws DaoException {
		dao.update("qualcosa", null);
	}



	@Test(expected = DaoException.class)
	public void updateDstEmptyName() throws DaoException {
		dao.update("qualcosa", "");
	}



	@Test
	public void updateWithExisingName() {
		try {
			dao.update(ALLERGENE_2B_RENAMED, SAMPLE_ALLERGENE_NAME);
		} catch (DaoException e) {
			Assert.assertTrue(e.getMessage().contains("Target entity already exists"));
		}
	}



	@Test
	public void deletionTest() throws DaoException {
		int expectedCount = dao.findAll().size();
		dao.delete("");
		dao.delete(null);
		int actualCount = dao.findAll().size();
		Assert.assertEquals(expectedCount, actualCount);
	}



	@Test
	public void deletionWhileReferencedByReport() {
		Assert.fail("not yet implemented");
	}


}
