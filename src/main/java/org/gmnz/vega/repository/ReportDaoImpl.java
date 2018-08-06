package org.gmnz.vega.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import org.gmnz.vega.domain.Report;


public class ReportDaoImpl extends BasicDaoImpl implements ReportDao {

	@Override
	public Collection<Report> findAll() throws DaoException {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery("SELECT * FROM report");

			// TODO elabora resultset

			// TODO restituisci il valore corretto
			return new HashSet<>();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.findAll error", e);
		} finally {
			releaseResources(s, rs);
		}
	}

}
