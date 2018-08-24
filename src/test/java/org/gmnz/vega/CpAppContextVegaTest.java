package org.gmnz.vega;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CpAppContextVegaTest {

	@Test
	public void dummyTest() throws SQLException {
		System.out.println("solo un test che non fallisce");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		System.out.println("ctx.getApplicationName() : " + ctx.getApplicationName());
		System.out.println("ctx.getDisplayName()     : " + ctx.getDisplayName());
		System.out.println("ctx.getId()              : " + ctx.getId());

		DataSource ds = (DataSource) ctx.getBean("dataSource");
		Connection c = ds.getConnection();
		System.out.println("connection = " + c);
		// non è necessario chiudere esplicitamente la connessione
		// perché viene chiusa automaticamente dal dataSource
		// c.close();
	}
}
