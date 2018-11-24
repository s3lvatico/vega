package org.gmnz.vega.domain;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class CategoryComparatorTest {

	private static Category c1;
	private static Category c2;

	private static CategoryComparator target;

	@Before
	public void setup() {
		c1 = Mockito.mock(Category.class);
		c2 = Mockito.mock(Category.class);
		Mockito.when(c1.getName()).thenReturn("CatA");
		Mockito.when(c2.getName()).thenReturn("CatB");

		target = new CategoryComparator();
	}

	@Test
	public void compare() {
		int comparison = target.compare(c1, c2);
		Assert.assertTrue(comparison < 0);

		comparison = target.compare(c2, c1);
		Assert.assertTrue(comparison > 0);

		comparison = target.compare(c1, c1);
		Assert.assertEquals(0, comparison);

		Assert.assertEquals(c1, c1);
		Assert.assertEquals(c2, c2);

		comparison = target.compare(null, null);
		Assert.assertEquals(0, comparison);

		comparison = target.compare(null, c1);
		Assert.assertTrue(comparison < 0);

		comparison = target.compare(c1, null);
		Assert.assertTrue(comparison > 0);

	}

	@Test public void hashcode() {
		String targetName = "Target category";
		Category target = new Category(targetName);
		Assert.assertEquals(target.hashCode(), targetName.hashCode());
	}
}
