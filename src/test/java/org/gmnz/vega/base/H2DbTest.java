package org.gmnz.vega.base;


import org.junit.Test;

import java.util.concurrent.TimeUnit;


/**
 * creato da simone in data 12/02/2018.
 */
public class H2DbTest extends BaseHbnDaoTest {

	@Test
	public void someTest() {
		try {
			TimeUnit.SECONDS.sleep(120);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println("interrotto");
		}
	}


}
