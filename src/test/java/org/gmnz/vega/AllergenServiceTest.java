package org.gmnz.vega;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.service.CategoryService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class AllergenServiceTest {

	private static Vega vega;



	@BeforeClass
	public static void initApplicationContext() {
		VegaSpringUtil.initSpring();
		vega = VegaFactory.getFactory().buildVega();
	}



	@Test
	public void getAllAllergens() throws VegaException {
		AllergenService svc = vega.getAllergenService();
		System.out.println(svc.getAllAllergens());
	}



	@Test
	public void createAllergenWrongParams() throws VegaException {
		AllergenService svc = vega.getAllergenService();
		try {
			svc.createAllergen(null, "someId");
		} catch (VegaException e) {
			System.err.println("(null, someId) : " + e.getMessage());
		}
		try {
			svc.createAllergen("", "someId");
		} catch (VegaException e) {
			System.err.println("(empty, someId) : " + e.getMessage());
		}
		try {
			svc.createAllergen("someName", null);
		} catch (VegaException e) {
			System.err.println("(someName, null) : " + e.getMessage());
		}
		try {
			svc.createAllergen("someName", "");
		} catch (VegaException e) {
			System.err.println("(someName, empty) : " + e.getMessage());
		}
		try {
			svc.createAllergen(null, null);
		} catch (VegaException e) {
			System.err.println("(null, null) : " + e.getMessage());
		}
		try {
			svc.createAllergen(null, "");
		} catch (VegaException e) {
			System.err.println("(null, empty) : " + e.getMessage());
		}
		try {
			svc.createAllergen("", null);
		} catch (VegaException e) {
			System.err.println("(empty, null) : " + e.getMessage());
		}
		try {
			svc.createAllergen("", "");
		} catch (VegaException e) {
			System.err.println("(empty, empty) : " + e.getMessage());
		}
	}



	@Test
	public void createAllergen() throws VegaException {
		CategoryService categoryService = vega.getCategoryService();
		AllergenService allergenService = vega.getAllergenService();
		String dummyAllergenId = null;
		String dummyCategoryId = null;
		try {
			dummyCategoryId = categoryService.createCategory("dummyCategoryName");

			String dummyAllergenName = "dummyAllergen";
			dummyAllergenId = allergenService.createAllergen(dummyAllergenName, dummyCategoryId);

			List<Allergen> allergens = allergenService.getAllAllergens();
			Assert.assertTrue(allergens.contains(new Allergen(dummyAllergenName)));

		} finally {
			removeEntityById("allergen", dummyAllergenId);
			removeEntityById("category", dummyCategoryId);
		}
	}



	private void removeEntityById(String tableName, String id) {
		String sqlQuery = String.format("DELETE FROM %s WHERE id = ?", tableName);
		DataSource ds = VegaSpringUtil.getSpringContext().getBean("dataSource", DataSource.class);
		JdbcTemplate tpl = new JdbcTemplate(ds);
		if (!VegaUtil.stringNullOrEmpty(id)) {
			tpl.update(sqlQuery, id);
		}
	}
}
