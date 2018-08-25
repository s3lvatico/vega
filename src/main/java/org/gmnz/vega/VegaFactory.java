package org.gmnz.vega;

import org.springframework.context.ApplicationContext;

public abstract class VegaFactory {

	private static final VegaFactory INSTANCE = new VegaFactory() {};

	private VegaFactory() {}

	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		VegaFactory.applicationContext = applicationContext;
	}

	public static VegaFactory getFactory() {
		return INSTANCE;
	}

	public Vega buildVega() {
		return applicationContext.getBean("vega", Vega.class);
	}


}
