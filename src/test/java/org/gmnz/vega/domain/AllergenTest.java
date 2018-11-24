package org.gmnz.vega.domain;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;


public class AllergenTest {

	@Test
	public void getNome() {
		final String aName = "testName";
		Allergen a = new Allergen(aName);
		Assert.assertEquals(aName, a.getName());
	}

	@Test
	public void equals() {
		Allergen x = new Allergen("x");
		Allergen y = new Allergen("y");

		Assert.assertNotEquals(x, y);

		Allergen x2 = new Allergen("x");

		Assert.assertEquals(x, x2);
	}


	@Test
	public void testToString() {
		Allergen x = new Allergen("x");
		String expected = "Allergen{name='x', category='DEFAULT_CATEGORY'}";
		Assert.assertEquals(expected, x.toString());
	}

	@Test
	public void testHashcode() {
		String allergenName = "ics";
		Allergen x = new Allergen(allergenName);
		int expected = allergenName.hashCode();
		Assert.assertEquals(expected, x.hashCode());
	}

	@Test
	public void sampleTest() {
		Allergen avena = new Allergen("Avena");
		Allergen farina = new Allergen("Farina");
		Allergen orzo = new Allergen("Orzo");
		Allergen patate = new Allergen("Patate");

		System.out.printf("%s - %d%n", avena, avena.hashCode());
		System.out.printf("%s - %d%n", farina, farina.hashCode());
		System.out.printf("%s - %d%n", orzo, orzo.hashCode());
		System.out.printf("%s - %d%n", patate, patate.hashCode());
	}

	@Test public void properties() {
		String id = UUID.randomUUID().toString();
		Allergen mock = Mockito.mock(Allergen.class);
		Mockito.when(mock.getId()).thenReturn(id);
		Allergen target = new Allergen("");
		target.setId(id);
		Assert.assertEquals(target.getId(), mock.getId());

		String randomName = UUID.randomUUID().toString();
		target.setName(randomName);
		Assert.assertEquals(randomName, target.getName());
	}
}
