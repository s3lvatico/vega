package org.gmnz.vega.domain;

import org.junit.Test;

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
}
