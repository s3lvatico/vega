package org.gmnz.vega.repository;


import java.sql.Connection;
import java.sql.SQLException;


class ConnectionOrientedDaoImpl implements ConnectionOrientedDao {

	protected Connection connection;



	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) { /* ignored */ }
		}
	}

}
