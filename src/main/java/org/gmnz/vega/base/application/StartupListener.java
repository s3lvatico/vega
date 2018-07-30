package org.gmnz.vega.base.application;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * creato da simone in data 30/07/2018.
 */
public class StartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.printf(">>> contextInitialized%n");

		String dummyJndiDataSource = sce.getServletContext().getInitParameter("dummy-jndi-datasource");
		System.out.printf("dummyJndiDataSource: <%s>%n", dummyJndiDataSource);

		System.out.printf("<<< contextInitialized%n");
	}



	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.printf(">>> contextDestroyed%n");
		System.out.printf("<<< contextDestroyed%n");
	}
}
