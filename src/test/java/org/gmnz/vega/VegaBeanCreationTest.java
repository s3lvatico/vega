package org.gmnz.vega;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VegaBeanCreationTest {

	static ApplicationContext ctx;

	@BeforeClass
	public static void initApplicationContext() {
		ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		VegaFactory.setApplicationContext(ctx);
	}

	@Test
	public void createVegaBean() throws VegaException {
		Vega v = VegaFactory.getInstance().createVega();
		System.out.println(v.getCategoryService().getAllCategories());
		System.out.println("created vega : " + v);
	}


	@AfterClass
	public static void afterClass() {
		System.out.println("end of test");
	}
}
