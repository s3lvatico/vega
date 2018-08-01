package org.gmnz.vega.repository;


import javax.sql.DataSource;


public abstract class DaoFactory {

	private static DaoFactory INSTANCE = new DaoFactory() {};

	/*
	 * in questa versione è molto semplice perché i DAO non hanno bisogno di
	 * connessioni sql
	 */

//	private DataSource dataSource;

//	private static final String ERR_CREATION = "unable to create the data access object";



	private DaoFactory() {}



	public static DaoFactory getInstance() {
		return INSTANCE;
	}



	public static void setDataSource(DataSource dataSource) {
//		INSTANCE.dataSource = dataSource;
	}



	public CategoryDao createCategoryDao() throws DaoCreationException {
		return new CategoryDaoImpl();
	}



	public AllergenDao createAllergenDao() throws DaoCreationException {
		return new AllergenDaoImpl();
	}
}
