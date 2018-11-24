package org.gmnz.vega.domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;


public class CategoryTest {

	@Test
	public void categoriaTest() {
		Category cereali = new Category("Cereali");

		Allergen avena = new Allergen("Avena");
		Allergen farina = new Allergen("Farina");
		Allergen orzo = new Allergen("Orzo");
		Allergen patate = new Allergen("Patate");

		cereali.addAllergen(avena);
		cereali.addAllergen(farina);
		cereali.addAllergen(orzo);
		cereali.addAllergen(patate);


		Category condimenti = new Category("Condimenti");

		Allergen lievitoDiBirra = new Allergen("Lievito di birra");
		Allergen olioDiOliva = new Allergen("Olio di oliva");
		Allergen strutto = new Allergen("Strutto");


		condimenti.addAllergen(lievitoDiBirra); condimenti.addAllergen(olioDiOliva); condimenti.addAllergen(strutto);



		System.out.println(cereali);

		System.out.println(condimenti);

		Allergen glucosio = new Allergen("Glucosio");
		condimenti.addAllergen(glucosio);
		System.out.println(condimenti);

//		condimenti.remove(glucosio);
//		Assert.assertEquals(3, condimenti.getAllergens().size());
//		System.out.println(condimenti);

	}

	@Test public void constructor() {
		String categoryName = "Sample Category name";
		Category target = new Category(categoryName);
		Assert.assertEquals("", target.getId());
		Assert.assertEquals(categoryName, target.getName());
		Assert.assertNotNull(target.getAllergens());
		Assert.assertEquals(0, target.getAllergens().size());
	}

	@Test public void properties() {
		Category target = new Category("");
		String id = UUID.randomUUID().toString();
		target.setId(id);
		Assert.assertEquals(id, target.getId());

		String name = UUID.randomUUID().toString();
		target.setName(name);
		Assert.assertEquals(name, target.getName());
	}

	@Test public void equals() {
		Category target = new Category("Target");
		Assert.assertEquals(target, target);
		Assert.assertNotEquals(target, null);
		Assert.assertNotEquals(target, new Object());

		Category theCopy = new Category("Target");
		Assert.assertEquals(target, theCopy);


	}
}
