package org.gmnz.vega.base;

import org.junit.Test;

public class HibernateBootstrapTest {

	@Test
	public void bootstrapTest() {
		HibernateUtil hibernateUtil = new HibernateUtil();

		hibernateUtil.bootstrap();
		hibernateUtil.shutdown();
	}
}
