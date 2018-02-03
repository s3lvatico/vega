package org.gmnz.vega.base;


import org.junit.AfterClass;
import org.junit.BeforeClass;


public class BaseHbnDaoTest {

	private static HibernateUtil hibernateUtil;



	@BeforeClass
	public static void boostrapHibernateSystem() {
		hibernateUtil = new HibernateUtil();
		hibernateUtil.bootstrap();
	}



	@AfterClass
	public static void shutdownHibernateSystem() {
		hibernateUtil.shutdown();
	}
}
