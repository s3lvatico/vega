package org.gmnz.vega;

import org.springframework.context.ApplicationContext;

public abstract class VegaFactory {

	private static final VegaFactory INSTANCE = new VegaFactory() {
	};

	private static ApplicationContext applicationContext;

	private VegaFactory() {

	}

	public static VegaFactory getInstance() {
		return INSTANCE;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		VegaFactory.applicationContext = applicationContext;
	}

	public Vega createVega() {
		return applicationContext.getBean("vega", Vega.class);
	}


}
