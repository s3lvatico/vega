package org.gmnz.vega.base.application;


import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;


/**
 * creato da simone in data 30/07/2018.
 */
public class StartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.printf(">>> contextInitialized%n");
		String dummyJndiDataSource = sce.getServletContext().getInitParameter("dummy-jndi-datasource");
		System.out.printf("dummyJndiDataSource: <%s>%n", dummyJndiDataSource);
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/vegaDS");
			Connection conn = ds.getConnection();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.printf("<<< contextInitialized%n");
	}



	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.printf(">>> contextDestroyed%n");
		System.out.printf("<<< contextDestroyed%n");
	}
}
