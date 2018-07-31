package org.gmnz.vega.base.application;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.gmnz.vega.repository.DaoFactory;


/**
 * creato da simone in data 30/07/2018.
 */
public class StartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(">>> contextInitialized");
		String jndiDataSource = sce.getServletContext().getInitParameter("jndi-datasource");
		System.out.printf("data source JNDI name: <%s>%n", jndiDataSource);
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/vegaDS");
			DaoFactory.setDataSource(ds);
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
