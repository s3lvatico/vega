package org.gmnz.vega.web;


import org.gmnz.vega.repository.DaoFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;


/**
 * creato da simone in data 30/07/2018.
 */
public class StartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// System.out.println(">>> contextInitialized");
		setupDataSource(sce.getServletContext());
		// System.out.printf("<<< contextInitialized%n");
	}

	private void setupDataSource(ServletContext ctx) {
		String jndiDataSource = ctx.getInitParameter("jndi-datasource");
		// System.out.printf("data source JNDI name: <%s>%n", jndiDataSource);
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup(jndiDataSource);
			DaoFactory.setDataSource(ds);
			System.out.println("StartupListener -- DaoFactory correctly initialized");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}



	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.printf(">>> contextDestroyed%n");
		System.out.printf("<<< contextDestroyed%n");
	}

}
