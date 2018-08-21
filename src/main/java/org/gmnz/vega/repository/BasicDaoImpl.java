package org.gmnz.vega.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;


class BasicDaoImpl extends ConnectionOrientedDaoImpl {

	protected JdbcTemplate jdbcTemplate;

	protected TransactionTemplate transactionTemplate;



	//@formatter:off
	@Deprecated
	protected void releaseResources(Statement s, ResultSet rs) {
		if (rs != null) { try { rs.close(); } catch (SQLException e) { /* ignored */ } }
		if (s != null)  { try { s.close();  } catch (SQLException e) { /* ignored */ } }
//		try { connection.close(); }           catch (SQLException e) { /* ignored */ }
	}
	//@formatter:on



	@Deprecated
	protected void releaseResources(Statement s) {
		releaseResources(s, null);
	}



	protected void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}



	protected void initTransactionTemplate(PlatformTransactionManager transactionManager) {
		transactionTemplate = new TransactionTemplate(transactionManager);
	}
}
