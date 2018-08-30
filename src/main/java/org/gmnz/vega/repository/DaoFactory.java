package org.gmnz.vega.repository;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/*
	18.824 : modificato da singleton a pojo
 */
public /* abstract */ class DaoFactory implements ApplicationContextAware {

	@Deprecated
	private DataSource dataSource;

	@Deprecated
	private PlatformTransactionManager transactionManager;

	private ApplicationContext applicationContext;



	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


/*
	DaoFactory(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
		this.dataSource = dataSource;
		this.transactionManager = platformTransactionManager;
	}

*/



	public CategoryDao createCategoryDao() {
//		CategoryDaoImpl daoImpl = new CategoryDaoImpl();
//		daoImpl.setDataSource(dataSource);
		return applicationContext.getBean("categoryDao", CategoryDao.class);
		// return daoImpl;
	}



	public AllergenDao createAllergenDao() {
//		AllergenDaoImpl daoImpl = new AllergenDaoImpl();
//		daoImpl.setDataSource(dataSource);
//		return daoImpl;
		return applicationContext.getBean("allergenDao", AllergenDao.class);
	}



	public ReportDao createReportDao() {
//		ReportDaoImpl daoImpl = new ReportDaoImpl();
//		daoImpl.setDataSource(dataSource);
//		daoImpl.initTransactionTemplate(transactionManager);
//		return daoImpl;
		return applicationContext.getBean("reportDao", ReportDao.class);
	}


}
