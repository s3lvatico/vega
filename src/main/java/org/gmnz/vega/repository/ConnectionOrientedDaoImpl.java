package org.gmnz.vega.repository;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * In questa versione i DAO usano connessioni gestite dal DataSource. Ciò significa che non è necessario gestirne la
 * chiusura manualmente, pertanto questa gerarchia di classi sarà resa deprecata
 *
 * @deprecated verrà rimosso in futuro
 */
@Deprecated
class ConnectionOrientedDaoImpl implements ConnectionOrientedDao {

	protected Connection connection;

	@Deprecated
	public void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) { /* ignored */ }
		}
	}

}
