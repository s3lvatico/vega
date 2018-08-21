package org.gmnz.vega.repository;


import javax.sql.DataSource;


public abstract class DaoFactory {

	private static DaoFactory INSTANCE = new DaoFactory() {
	};

	private DataSource dataSource;

//	private static final String ERR_CREATION = "unable to create the data access object";



	private DaoFactory() {
	}



	public static DaoFactory getInstance() {
		return INSTANCE;
	}



	public static void setDataSource(DataSource dataSource) {
		INSTANCE.dataSource = dataSource;
	}



	public CategoryDao createCategoryDao() {
		CategoryDaoImpl daoImpl = new CategoryDaoImpl();
		daoImpl.setDataSource(dataSource);
		return daoImpl;
	}



	public AllergenDao createAllergenDao() {
		AllergenDaoImpl daoImpl = new AllergenDaoImpl();
//		injectConnection(daoImpl);
		daoImpl.setDataSource(dataSource);
		return daoImpl;
	}



	public ReportDao createReportDao() throws DaoCreationException {
		ReportDaoImpl daoImpl = new ReportDaoImpl();
//		injectConnection(daoImpl);
		daoImpl.setDataSource(dataSource);
		return daoImpl;
	}



//	@Deprecated
//	private void injectConnection(ConnectionOrientedDaoImpl dao) throws DaoCreationException {
//		try {
//			dao.connection = dataSource.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoCreationException(ERR_CREATION, e);
//		}
//	}

}
