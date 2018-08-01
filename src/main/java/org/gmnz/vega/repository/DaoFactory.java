package org.gmnz.vega.repository;


import java.sql.SQLException;

import javax.sql.DataSource;


public abstract class DaoFactory {

	private static DaoFactory INSTANCE = new DaoFactory() {};

	private DataSource dataSource;

	private static final String ERR_CREATION = "unable to create the data access object";



	private DaoFactory() {}



	public static DaoFactory getInstance() {
		return INSTANCE;
	}



	public static void setDataSource(DataSource dataSource) {
		INSTANCE.dataSource = dataSource;
	}



	public CategoryDao createCategoryDao() throws DaoCreationException {
		CategoryDaoImpl daoImpl = new CategoryDaoImpl();
		try {
			daoImpl.connection = dataSource.getConnection();
			return daoImpl;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoCreationException(ERR_CREATION, e);
		}
	}



	public AllergenDao createAllergenDao() throws DaoCreationException {
		AllergenDaoImpl daoImpl = new AllergenDaoImpl();
		try {
			daoImpl.connection = dataSource.getConnection();
			return daoImpl;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoCreationException(ERR_CREATION, e);
		}
	}
}
