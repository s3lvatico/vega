package org.gmnz.vega.web;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.gmnz.vega.VegaSpringUtil;


/**
 * creato da simone in data 30/07/2018.
 */
public class StartupListener implements ServletContextListener {

	// private ApplicationContext springCtx;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(">>> contextInitialized");

		VegaSpringUtil.initSpring();

		System.out.printf("<<< contextInitialized%n");
	}



	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.printf(">>> contextDestroyed%n");
		// springCtx = null;
		System.out.printf("<<< contextDestroyed%n");
	}
}
