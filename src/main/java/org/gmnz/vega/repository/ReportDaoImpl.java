package org.gmnz.vega.repository;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ToxicityRating;


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



	@Override
	public void createReport(Report r) throws DaoException {
		PreparedStatement psRptHeader = null;
		PreparedStatement psRptDetail = null;
		try {
			connection.setAutoCommit(false);
			psRptHeader = connection.prepareStatement("INSERT INTO report VALUES (?, ?, ?)");
			psRptHeader.setString(1, r.getId());
			psRptHeader.setString(2, r.getSubjectName());
			psRptHeader.setDate(3, new Date(r.getCreationDate().getTime()));
			psRptHeader.execute();

			psRptDetail = connection.prepareStatement("INSERT INTO report_line VALUES (?, ?, ?)");
			for (String category : r.getCategories()) {
				for (ToxicityRating tr : r.getRatings(category)) {
					psRptDetail.setString(1, tr.getAllergen().getId());
					psRptDetail.setString(2, r.getId());					
					psRptDetail.setDouble(3, tr.getToxicity());
					psRptDetail.execute();
				}
			}			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.createReport error", e);
		} finally {
			releaseResources(psRptHeader);
			releaseResources(psRptDetail);
		}
	}

}
