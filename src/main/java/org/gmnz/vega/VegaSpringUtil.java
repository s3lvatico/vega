package org.gmnz.vega;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * creato da simone in data 25/08/2018.
 */
public class VegaSpringUtil {

	public static void initSpring() {
		ApplicationContext springCtx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		VegaFactory.setApplicationContext(springCtx);
	}
}
