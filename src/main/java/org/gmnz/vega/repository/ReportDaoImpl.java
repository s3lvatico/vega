package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ToxicityRating;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class ReportDaoImpl extends BasicDaoImpl implements ReportDao {

	@Override
	public Collection<Report> findAll() throws DaoException {

//@formatter:off
		String sqlQuery = "select " +
								" rpt.id, " +
								" rpt.subject_name, " +
								" rpt.date_creation, " +
								" owner.user_name, " +
								" owner.full_name " +
								"from " +
								" report rpt " +
								" join vega_user owner  on rpt.owner = owner.user_name " +
								"order by " +
								" subject_name, " +
								" date_creation;";
//@formatter:on
		return jdbcTemplate.query(sqlQuery, new RowMapper<Report>() {
			@Override
			public Report mapRow(ResultSet resultSet, int i) throws SQLException {
				String id = resultSet.getString(1);
				String subjectName = resultSet.getString(2);
				Date creationDate = resultSet.getDate(3);
				String owner = resultSet.getString(4);
				Report r = new Report(id, subjectName, creationDate, owner);
				r.setOwnerFullName(resultSet.getString(5));
				return r;
			}
		});


//		Statement s = null;
//		ResultSet rs = null;
//		try {
//			s = connection.createStatement();
////@formatter:off
//			String sqlQuery = "select " +
//									" rpt.id, " +
//									" rpt.subject_name, " +
//									" rpt.date_creation, " +
//									" owner.user_name, " +
//									" owner.full_name " +
//									"from " +
//									" report rpt " +
//									" join vega_user owner  on rpt.owner = owner.user_name " +
//									"order by " +
//									" subject_name, " +
//									" date_creation;";
////@formatter:on
//			// rs = s.executeQuery("SELECT * FROM report ORDER BY subject_name, date_creation");
//			rs = s.executeQuery(sqlQuery);
//			List<Report> reports = new ArrayList<>();
//			while (rs.next()) {
//				String id = rs.getString(1);
//				String subjectName = rs.getString(2);
//				Date creationDate = rs.getDate(3);
//				String owner = rs.getString(4);
//				Report r = new Report(id, subjectName, creationDate, owner);
//				r.setOwnerFullName(rs.getString(5));
//				reports.add(r);
//			}
//			return reports;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("ReportDaoImpl.findAll error", e);
//		} finally {
//			releaseResources(s, rs);
//		}
	}



	@Override
	public void createReport(Report r) throws DaoException {
		PreparedStatement psRptHeader = null;
		PreparedStatement psRptDetail = null;
		try {
			connection.setAutoCommit(false);
			psRptHeader = connection.prepareStatement("INSERT INTO report VALUES (?, ?, ?, ?)");
			psRptHeader.setString(1, r.getId());
			psRptHeader.setString(2, r.getSubjectName());
			psRptHeader.setTimestamp(3, new Timestamp(r.getCreationDate().getTime()));
			psRptHeader.setString(4, r.getOwner());
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
//@formatter:off
			String sqlQuery = "select " +
					" rpt_head.subject_name, " +
					" rpt_head.date_creation, " +
					" rpt_head.owner, " +
					" vega_user.full_name owner_full_name, " +
					" cat.e_name category_name, " +
					" al.e_name allergen_name, " +
					" rpt_detail.toxicity " +
					"from " +
					" report rpt_head " +
					"join report_line rpt_detail on  rpt_head.id = rpt_detail.id_report " +
					"join allergen al on  rpt_detail.id_allergen = al.id " +
					"join category cat on  al.id_category = cat.id " +
					"join vega_user on  rpt_head.owner = vega_user.user_name " +
					"where  rpt_head.id = ? " +
					"order by cat.e_name,  allergen_name";
//@formatter:on
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, id);
			rs = ps.executeQuery();
			Report r = null;
			while (rs.next()) {
				if (r == null) {
					Timestamp ts = rs.getTimestamp(2);
					r = new Report(rs.getString(1), new java.util.Date(ts.getTime()), rs.getString(3));
					r.setOwnerFullName(rs.getString(4));
				}
				Category c = new Category(rs.getString("category_name"));
				Allergen a = new Allergen(rs.getString("allergen_name"));
				a.setCategory(c);

				ToxicityRating tr = new ToxicityRating(a, rs.getDouble(7));
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



	static class BasicReportRowMapper implements RowMapper<Report> {

		@Override
		public Report mapRow(ResultSet resultSet, int i) throws SQLException {
			String rptId = resultSet.getString(1);
			String subjectName = resultSet.getString(2);
			Timestamp ts = resultSet.getTimestamp(3);
			java.util.Date rptCreationDate = new Date(ts.getTime());
			String rptOwner = resultSet.getString(4);
			return new Report(rptId, subjectName, rptCreationDate, rptOwner);
		}
	}



	@Override
	public Report getSummaryById(String id) throws DaoException {
		String sqlQuery = "SELECT * FROM report WHERE id = ?";
		return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BasicReportRowMapper());

//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			ps = connection.prepareStatement("SELECT * FROM report WHERE id = ?");
//			ps.setString(1, id);
//			rs = ps.executeQuery();
//			Report r = null;
//			if (rs.next()) {
//				String rptId = rs.getString(1);
//				String subjectName = rs.getString(2);
//				Timestamp ts = rs.getTimestamp(3);
//				java.util.Date rptCreationDate = new Date(ts.getTime());
//				String rptOwner = rs.getString(4);
//				r = new Report(rptId, subjectName, rptCreationDate, rptOwner);
//			}
//			return r;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("ReportDaoImpl.getSummryById error", e);
//		} finally {
//			releaseResources(ps, rs);
//		}

	}



	@Override
	public void remove(String id) throws DaoException {
		String sqlStatement = "DELETE FROM report WHERE id = ?";
		jdbcTemplate.update(sqlStatement, id);

//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			ps = connection.prepareStatement("DELETE FROM report WHERE id = ?");
//			ps.setString(1, id);
//			ps.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DaoException("ReportDaoImpl.remove error", e);
//		} finally {
//			releaseResources(ps, rs);
//		}

	}

}
