package org.gmnz.vega.repository;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class BasicDaoImpl {

	protected Connection connection;



//@formatter:off
	protected void releaseResources(Statement s, ResultSet rs) {
		if (rs != null) { try { rs.close(); } catch (SQLException e) { /* ignored */ } }
		if (s != null)  { try { s.close();  } catch (SQLException e) { /* ignored */ } }
		try { connection.close(); }           catch (SQLException e) { /* ignored */ }
	}
//@formatter:on



	protected void releaseResources(Statement s) {
		releaseResources(s, null);
	}
}
