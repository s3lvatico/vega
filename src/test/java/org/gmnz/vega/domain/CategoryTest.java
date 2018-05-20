package org.gmnz.vega.domain;

import org.junit.Assert;
import org.junit.Test;

public class CategoryTest {

	@Test
	public void categoriaTest() {
		Category cereali = new Category("Cereali");

		Allergen avena = new Allergen("Avena");
		Allergen farina = new Allergen("Farina");
		Allergen orzo = new Allergen("Orzo");
		Allergen patate = new Allergen("Patate");

		cereali.add(avena);
		cereali.add(farina);
		cereali.add(orzo);
		cereali.add(patate);


		Category condimenti = new Category("Condimenti");

		Allergen lievitoDiBirra = new Allergen("Lievito di birra");
		Allergen olioDiOliva = new Allergen("Olio di oliva");
		Allergen strutto = new Allergen("Strutto");


		condimenti.add(lievitoDiBirra); condimenti.add(olioDiOliva); condimenti.add(strutto);



		System.out.println(cereali);

		System.out.println(condimenti);

		Allergen glucosio = new Allergen("Glucosio");
		condimenti.add(glucosio);
		System.out.println(condimenti);

		condimenti.remove(glucosio);
		Assert.assertEquals(3, condimenti.getAllergens().size());
		System.out.println(condimenti);


	}
}
