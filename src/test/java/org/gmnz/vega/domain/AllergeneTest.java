package org.gmnz.vega.domain;


import org.junit.Assert;
import org.junit.Test;

public class AllergeneTest {

	@Test
	public void getNome() {
		final String aName = "testName";
		Allergene a = new Allergene(aName);
		Assert.assertEquals(aName, a.getNome());
	}

	@Test
	public void equals() {
		Allergene x = new Allergene("x");
		Allergene y = new Allergene("y");

		Assert.assertNotEquals(x, y);

		Allergene x2 = new Allergene("x");

		Assert.assertEquals(x, x2);
	}


	@Test
	public void testToString() {
		Allergene x = new Allergene("x");
		String strX = "Allergene{nome='x'}";
		Assert.assertEquals(strX, x.toString());
	}

	@Test
	public void testHashcode() {
		Allergene x = new Allergene("ics");
		int expected = 104089;
		Assert.assertEquals(expected, x.hashCode());
	}

	@Test
	public void sampleTest() {
		Allergene avena = new Allergene("Avena");
		Allergene farina = new Allergene("Farina");
		Allergene orzo = new Allergene("Orzo");
		Allergene patate = new Allergene("Patate");

		System.out.printf("%s - %d%n", avena, avena.hashCode());
		System.out.printf("%s - %d%n", farina, farina.hashCode());
		System.out.printf("%s - %d%n", orzo, orzo.hashCode());
		System.out.printf("%s - %d%n", patate, patate.hashCode());
	}

}
