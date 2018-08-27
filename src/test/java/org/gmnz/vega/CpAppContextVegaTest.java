package org.gmnz.vega;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CpAppContextVegaTest {

	@Test
	public void dummyTest() throws SQLException {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml")) {
			System.out.println("ctx.getApplicationName() : " + ctx.getApplicationName());
			System.out.println("ctx.getDisplayName()     : " + ctx.getDisplayName());
			System.out.println("ctx.getId()              : " + ctx.getId());

			DataSource ds = (DataSource) ctx.getBean("dataSource");
			Connection c = ds.getConnection();
			System.out.println("connection = " + c);

		} finally {
			System.out.println("done");
		}
	}
}
