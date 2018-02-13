package org.gmnz.vega;


import org.h2.tools.Server;

import java.sql.SQLException;


/**
 * creato da simone in data 13/02/2018.
 */
public class H2TestDbBoostrap implements TestDbBootstrap {
	private Server server;



	@Override
	public void setupDbServer() {
		try {
			server = Server.createTcpServer().start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void shutdownDbServer() {
		if (server != null) {
			server.shutdown();
		}
	}
}
