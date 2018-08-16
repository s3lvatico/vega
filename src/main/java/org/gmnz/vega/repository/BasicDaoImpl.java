package org.gmnz.vega.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


class BasicDaoImpl extends ConnectionOrientedDaoImpl {

	//@formatter:off
	protected void releaseResources(Statement s, ResultSet rs) {
		if (rs != null) { try { rs.close(); } catch (SQLException e) { /* ignored */ } }
		if (s != null)  { try { s.close();  } catch (SQLException e) { /* ignored */ } }
//		try { connection.close(); }           catch (SQLException e) { /* ignored */ }
	}
	//@formatter:on



	protected void releaseResources(Statement s) {
		releaseResources(s, null);
	}

	protected JdbcTemplate jdbcTemplate;



	protected void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
}
