package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ToxicityRating;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
			//psRptHeader.setDate(3, new Date(r.getCreationDate().getTime()));
			psRptHeader.setTimestamp(3, new Timestamp(r.getCreationDate().getTime()));
			psRptHeader.execute();

			psRptDetail = connection.prepareStatement("INSERT INTO report_line VALUES (?, ?, ?)");
			for (String category : r.getCategories()) {
				for (ToxicityRating tr : r.getRatings(category)) {
					psRptDetail.setString(1, r.getId());
					psRptDetail.setString(2, tr.getAllergen().getId());
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



	@Override
	public Report findById(String id) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sqlQuery = "select " +
					" rpt_head.subject_name, " +
					" rpt_head.date_creation, " +
					" cat.e_name category_name, " +
					" al.e_name allergen_name, " +
					" rpt_detail.toxicity " +
					"from " +
					" report rpt_head " +
					"join report_line rpt_detail on " +
					" rpt_head.id = rpt_detail.id_report " +
					"join allergen al on " +
					" rpt_detail.id_allergen = al.id " +
					"join category cat on " +
					" al.id_category = cat.id " +
					"where " +
					" rpt_head.id = ? " +
					"order by " +
					" cat.e_name, " +
					" allergen_name";
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, id);
			rs = ps.executeQuery();
			Report r = null;
			while (rs.next()) {
				if (r == null) {
					Timestamp ts = rs.getTimestamp(2);
					r = new Report(rs.getString(1), new java.util.Date(ts.getTime()));
				}
				Category c = new Category(rs.getString("category_name"));
				Allergen a = new Allergen(rs.getString("allergen_name"));
				a.setCategory(c);

				ToxicityRating tr = new ToxicityRating(a, rs.getDouble(5));
				r.addRating(tr);
			}
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.findById error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



	@Override
	public Report getSummryById(String id) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM report WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			Report r = null;
			if (rs.next()) {
				String rptId = rs.getString(1);
				String subjectName = rs.getString(2);
				Timestamp ts = rs.getTimestamp(3);
				java.util.Date rptCreationDate = new Date(ts.getTime());
				r = new Report(rptId, subjectName, rptCreationDate);
			}
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.getSummryById error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}


}
