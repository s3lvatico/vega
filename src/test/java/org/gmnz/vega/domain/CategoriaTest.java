package org.gmnz.vega.domain;

import org.junit.Assert;
import org.junit.Test;

public class CategoriaTest {

	@Test
	public void categoriaTest() {
		Categoria cereali = new Categoria("Cereali");

		Allergene avena = new Allergene("Avena");
		Allergene farina = new Allergene("Farina");
		Allergene orzo = new Allergene("Orzo");
		Allergene patate = new Allergene("Patate");

		cereali.add(avena);
		cereali.add(farina);
		cereali.add(orzo);
		cereali.add(patate);


		Categoria condimenti = new Categoria("Condimenti");

		Allergene lievitoDiBirra = new Allergene("Lievito di birra");
		Allergene olioDiOliva = new Allergene("Olio di oliva");
		Allergene strutto = new Allergene("Strutto");


		condimenti.add(lievitoDiBirra); condimenti.add(olioDiOliva); condimenti.add(strutto);



		System.out.println(cereali);

		System.out.println(condimenti);

		Allergene glucosio = new Allergene("Glucosio");
		condimenti.add(glucosio);
		System.out.println(condimenti);

		condimenti.remove(glucosio);
		Assert.assertEquals(3, condimenti.getAllergeni().size());
		System.out.println(condimenti);


	}
}
