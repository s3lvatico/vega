package org.gmnz.vega.repository;


import org.gmnz.vega.domain.*;

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
									" date_creation ";
//@formatter:on
			rs = s.executeQuery(sqlQuery);
			List<Report> reports = new ArrayList<>();
			while (rs.next()) {
				// String id = rs.getString(1);
				String subjectName = rs.getString(2);
				Timestamp creationDate = rs.getTimestamp(3);
				String owner = rs.getString(4);
				ReportBuilder builder = ReportBuilder.getBuilder();
				builder.subjectName(subjectName).createdOn(creationDate).owner(owner);
				Report r = builder.build();
				r.setOwnerFullName(rs.getString(5));
				reports.add(r);
			}
			return reports;
		}
		catch (SQLException | ReportBuildException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.findAll error", e);
		}
		finally {
			releaseResources(s, rs);
		}
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
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.createReport error", e);
		}
		finally {
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
					"left join report_line rpt_detail on  rpt_head.id = rpt_detail.id_report " +
					"left join allergen al on  rpt_detail.id_allergen = al.id " +
					"left join category cat on  al.id_category = cat.id " +
					"left join vega_user on  rpt_head.owner = vega_user.user_name " +
					"where  rpt_head.id = ? " +
					"order by cat.e_name,  allergen_name";
//@formatter:off
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, id);
			rs = ps.executeQuery();
			Report r = null;
			while (rs.next()) {
				if (r == null) {
					Timestamp ts = rs.getTimestamp(2);
					String subjectName = rs.getString(1);
					String owner = rs.getString(3);

					ReportBuilder builder = ReportBuilder.getBuilder();
					r = builder.subjectName(subjectName).createdOn(ts).owner(owner).build();
					r.setOwnerFullName(rs.getString(4));
				}
				Category c = new Category(rs.getString("category_name"));
				Allergen a = new Allergen(rs.getString("allergen_name"));
				a.setCategory(c);

				ToxicityRating tr = new ToxicityRating(a, rs.getDouble(7));
				r.addRating(tr);
			}
			return r;
		} catch (SQLException | ReportBuildException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.findById error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



	@Override
	public Report getSummaryById(String id) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM report WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			Report r = null;
			if (rs.next()) {
				String subjectName = rs.getString(2);
				Timestamp ts = rs.getTimestamp(3);
				String owner = rs.getString(4);
				ReportBuilder builder = ReportBuilder.getBuilder();
				builder.subjectName(subjectName).createdOn(ts).owner(owner);
				r = builder.build();
			}
			return r;
		} catch (SQLException | ReportBuildException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.getSummryById error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



	@Override
	public void remove(String id) throws DaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("DELETE FROM report WHERE id = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.remove error", e);
		} finally {
			releaseResources(ps, rs);
		}
	}



	@Override
	public void removeByOwnerId(String ownerId) throws DaoException {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("DELETE FROM report WHERE owner = ?");
			ps.setString(1, ownerId);
			ps.execute();
			/*
			non dovrebbe essere necessario eliminare anche le righe di dettaglio
			perché la FK ha il cascade on delete
			 */
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("ReportDaoImpl.removeByOwnerId error", e);
		} finally {
			releaseResources(ps);
		}
	}

}
