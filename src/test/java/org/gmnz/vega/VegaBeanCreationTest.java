package org.gmnz.vega;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class VegaBeanCreationTest {




	@BeforeClass
	public static void initApplicationContext() {
		VegaSpringUtil.initSpring();
	}



	@Test
	public void createVegaBean() throws VegaException {
		Vega v = VegaFactory.getFactory().buildVega();
		System.out.println(v.getCategoryService().getAllCategories());
		System.out.println("created vega : " + v);
	}




	@AfterClass
	public static void afterClass() {
		System.out.println("end of test");
	}
}
