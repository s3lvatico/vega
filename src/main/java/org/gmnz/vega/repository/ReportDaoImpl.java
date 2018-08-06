package org.gmnz.vega.repository;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gmnz.vega.domain.Report;


class ReportDaoImpl extends BasicDaoImpl implements ReportDao {

	@Override
	public Collection<Report> findAll() throws DaoException {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = connection.createStatement();
			rs = s.executeQuery("SELECT * FROM report ORDER BY subject_name, date_creation");
			List<Report> reports = new ArrayList<>();
			while (rs.next()) {
				String id = rs.getString(1);
				String subjectName = rs.getString(2);
				Date creationDate = rs.getDate(3);
				Report r = new Report(id, subjectName, creationDate);
				reports.add(r);
			}
			return reports;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.findAll error", e);
		} finally {
			releaseResources(s, rs);
		}
	}

}
