package org.gmnz.vega.web;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.gmnz.vega.repository.DaoFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * creato da simone in data 30/07/2018.
 */
public class StartupListener implements ServletContextListener {

	private ApplicationContext springCtx;



	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(">>> contextInitialized");

//		String jndiDataSource = sce.getServletContext().getInitParameter("jndi-datasource");
//		System.out.printf("data source JNDI name: <%s>%n", jndiDataSource);
//		try {
//			Context initContext = new InitialContext();
//			Context envContext = (Context) initContext.lookup("java:/comp/env");
//			DataSource ds = (DataSource) envContext.lookup("jdbc/vegaDS");
//			DaoFactory.setDataSource(ds);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		springCtx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		DataSource ds = springCtx.getBean("dataSource", DataSource.class);
		PlatformTransactionManager transactionManager = springCtx.getBean("transactionManager",
				PlatformTransactionManager.class);
		DaoFactory.setDataSource(ds);
		DaoFactory.setTransactionManager(transactionManager);

		System.out.printf("<<< contextInitialized%n");
	}



	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.printf(">>> contextDestroyed%n");
		springCtx = null;
		System.out.printf("<<< contextDestroyed%n");
	}
}
