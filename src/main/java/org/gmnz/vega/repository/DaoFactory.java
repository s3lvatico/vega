package org.gmnz.vega.repository;


import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


public abstract class DaoFactory {

	// TODO questo può diventare un bean gestito da Spring

	private static DaoFactory INSTANCE = new DaoFactory() {
	};

	private DataSource dataSource;

	private PlatformTransactionManager transactionManager;



	private DaoFactory() {
	}



	public static DaoFactory getInstance() {
		return INSTANCE;
	}



	public static void setDataSource(DataSource dataSource) {
		INSTANCE.dataSource = dataSource;
	}



	public static void setTransactionManager(PlatformTransactionManager transactionManager) {
		INSTANCE.transactionManager = transactionManager;
	}



	public CategoryDao createCategoryDao() {
		CategoryDaoImpl daoImpl = new CategoryDaoImpl();
		daoImpl.setDataSource(dataSource);
		return daoImpl;
	}



	public AllergenDao createAllergenDao() {
		AllergenDaoImpl daoImpl = new AllergenDaoImpl();
		daoImpl.setDataSource(dataSource);
		return daoImpl;
	}



	public ReportDao createReportDao() throws DaoCreationException {
		ReportDaoImpl daoImpl = new ReportDaoImpl();
		daoImpl.setDataSource(dataSource);
		daoImpl.initTransactionTemplate(transactionManager);
		return daoImpl;
	}

}
