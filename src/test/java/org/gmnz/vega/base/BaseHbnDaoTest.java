package org.gmnz.vega.base;


import org.gmnz.vega.TestDbBootstrap;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.Properties;


public class BaseHbnDaoTest {

	private static HibernateUtil hibernateUtil;

	private static TestDbBootstrap testDbBootstrap;

	private static boolean dbBoostrapSuccessful;



	private static boolean createTestDbBoostrap() {
		Properties p = new Properties();
		try {
			p.load(BaseHbnDaoTest.class.getResourceAsStream("/testDb.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		String setupClassName = p.getProperty("setup.class.name");
		try {
			testDbBootstrap = (TestDbBootstrap) Class.forName(setupClassName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}



	@BeforeClass
	public static void boostrapHibernateSystem() {
		if (!createTestDbBoostrap()) {
			dbBoostrapSuccessful = false;
		} else {
			dbBoostrapSuccessful = true;
			testDbBootstrap.setupDbServer();
			hibernateUtil = new HibernateUtil();
			hibernateUtil.bootstrap();
		}
	}



	@AfterClass
	public static void shutdownHibernateSystem() {
		if (dbBoostrapSuccessful) {
			hibernateUtil.shutdown();
			testDbBootstrap.shutdownDbServer();
		}
	}
}
