package org.gmnz.vega;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * creato da simone in data 25/08/2018.
 */
public class VegaSpringUtil {

	private static ApplicationContext ctx;



	public static void initSpring() {
		ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		VegaFactory.setApplicationContext(ctx);
	}



	public static ApplicationContext getSpringContext() {
		return ctx;
	}
}
