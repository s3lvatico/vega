package org.gmnz.vega.repository;


import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/*
	18.824 : modificato da singleton a pojo
 */
public /* abstract */ class DaoFactory {

	// TODO valuta se si può trasformare in un AppContext aware e ottenere i dao come bean
	private DataSource dataSource;

	private PlatformTransactionManager transactionManager;



	/* private */ DaoFactory(DataSource dataSource, PlatformTransactionManager platformTransactionManager) {
		this.dataSource = dataSource;
		this.transactionManager = platformTransactionManager;
	}


/*
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
*/

/*
	public static void setDataSource(DataSource dataSource) {
		INSTANCE.dataSource = dataSource;
	}



	public static void setTransactionManager(PlatformTransactionManager transactionManager) {
		INSTANCE.transactionManager = transactionManager;
	}
*/


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
