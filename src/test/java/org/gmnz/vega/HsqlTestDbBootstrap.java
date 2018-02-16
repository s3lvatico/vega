package org.gmnz.vega;


import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;

import java.io.IOException;


/**
 * creato da simone in data 16/02/2018.
 */
public class HsqlTestDbBootstrap implements TestDbBootstrap {

	Server server;



	@Override
	public void setupDbServer() {
		HsqlProperties p = new HsqlProperties();
		p.setProperty("server.database.0", "file:./db/vega");
		p.setProperty("server.dbname.0", "vega");

		server = new Server();
		try {
			server.setProperties(p);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerAcl.AclFormatException e) {
			e.printStackTrace();
		}
	}




	@Override
	public void shutdownDbServer() {
		server.shutdownCatalogs(1);
	}
}
