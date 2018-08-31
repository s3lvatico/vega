package org.gmnz.vega.repository;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class DaoFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;



	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}



	public CategoryDao createCategoryDao() {
		return applicationContext.getBean("categoryDao", CategoryDao.class);
	}



	public AllergenDao createAllergenDao() {
		return applicationContext.getBean("allergenDao", AllergenDao.class);
	}



	public ReportDao createReportDao() {
		return applicationContext.getBean("reportDao", ReportDao.class);
	}

}
