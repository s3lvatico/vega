package org.gmnz.vega.repository;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import javax.sql.DataSource;

import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ReportBuildException;
import org.gmnz.vega.domain.ReportBuilder;
import org.gmnz.vega.domain.ToxicityRating;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;


class ReportDaoImpl extends BasicDaoImpl implements ReportDao {

	protected ReportDaoImpl(DataSource dataSource, PlatformTransactionManager transactionManager) {
		super(dataSource, transactionManager);
	}



	@Override
	public Collection<Report> findAll() throws DaoException {

//@formatter:off
		String sqlQuery = "SELECT " +
								" rpt.id, " +
								" rpt.subject_name, " +
								" rpt.date_creation, " +
								" owner.user_name, " +
								" owner.full_name " +
								"FROM " +
								" report rpt " +
								" JOIN vega_user owner  ON rpt.owner = owner.user_name " +
								"ORDER BY " +
								" subject_name, " +
								" date_creation ";
//@formatter:on
		return jdbcTemplate.query(sqlQuery, new RowMapper<Report>() {

			@Override
			public Report mapRow(ResultSet resultSet, int i) throws SQLException {
//				String id = resultSet.getString(1);
				String subjectName = resultSet.getString(2);
				Date creationDate = resultSet.getDate(3);
				String owner = resultSet.getString(4);
				// Report r = new Report(id, subjectName, creationDate, owner);
				ReportBuilder builder = ReportBuilder.getBuilder();
				Report r = null;
				try {
					r = builder.subjectName(subjectName).createdOn(creationDate).owner(owner).build();
				} catch (ReportBuildException e) {
					e.printStackTrace();
					throw new SQLException("could not build the Report object", e);
				}
				r.setOwnerFullName(resultSet.getString(5));
				return r;
			}
		});
	}



	@Override
	public void createReport(Report r) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				String sqlInsertHeader = "INSERT INTO report VALUES (?, ?, ?, ?)";
				String sqlInsertDetail = "INSERT INTO report_line VALUES (?, ?, ?)";

				//@formatter:off
				Object[] headerParams = { 
					r.getId(), 
					r.getSubjectName(), 
					new Timestamp(r.getCreationDate().getTime()),
					r.getOwner() 
				}; //@formatter:on
				jdbcTemplate.update(sqlInsertHeader, headerParams);

				for (String category : r.getCategories()) {
					for (ToxicityRating tr : r.getRatings(category)) {
						//@formatter:off
						Object[] detailParams =  {
							r.getId(),
							tr.getAllergen().getId(),
							tr.getToxicity()
						}; //@formatter:on
						jdbcTemplate.update(sqlInsertDetail, detailParams);
					} // ~for
				} // ~for
			} // ~ doInTransactionWithoutResult
		});
	}

	static class ReportRsExtractor implements ResultSetExtractor<Report> {

		@Override
		public Report extractData(ResultSet rs) throws SQLException, DataAccessException {
			Report r = null;
			while (rs.next()) {
				if (r == null) {
					Timestamp ts = rs.getTimestamp(2);
					ReportBuilder builder = ReportBuilder.getBuilder();
					builder.subjectName(rs.getString(1)).owner(rs.getString(3));
					builder.createdOn(new java.util.Date(ts.getTime()));
					// r = new Report(rs.getString(1), new java.util.Date(ts.getTime()),
					// rs.getString(3));
					try {
						r = builder.build();
					} catch (ReportBuildException e) {
						throw new SQLException("could not build the Report object", e);
					}
					r.setOwnerFullName(rs.getString(4));
				}
				Category c = new Category(rs.getString("category_name"));
				Allergen a = new Allergen(rs.getString("allergen_name"));
				a.setCategory(c);

				ToxicityRating tr = new ToxicityRating(a, rs.getDouble(7));
				r.addRating(tr);
			} // ~while
			return r;
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

			ReportBuilder builder = ReportBuilder.getBuilder();
			builder.subjectName(subjectName).createdOn(rptCreationDate).owner(rptOwner);
			try {
				// return new Report(rptId, subjectName, rptCreationDate, rptOwner);
				return builder.build();
			} catch (ReportBuildException e) {
				throw new SQLException("could not build the Report object", e);
			}

		}

	}



	@Override
	public Report findById(String id) throws DaoException {
		//@formatter:off
		String sqlQuery = "SELECT " +
				" rpt_head.subject_name, " +
				" rpt_head.date_creation, " +
				" rpt_head.owner, " +
				" vega_user.full_name owner_full_name, " +
				" cat.e_name category_name, " +
				" al.e_name allergen_name, " +
				" rpt_detail.toxicity " +
				"FROM " +
				" report rpt_head " +
				"JOIN report_line rpt_detail ON rpt_head.id = rpt_detail.id_report " +
				"JOIN allergen al ON rpt_detail.id_allergen = al.id " +
				"JOIN category cat ON al.id_category = cat.id " +
				"JOIN vega_user ON rpt_head.owner = vega_user.user_name " +
				"WHERE  rpt_head.id = ? " +
				"ORDER BY cat.e_name, allergen_name";
		//@formatter:on

		return jdbcTemplate.query(sqlQuery, new Object[] { id }, new ReportRsExtractor());

	}



	@Override
	public Report getSummaryById(String id) throws DaoException {
		String sqlQuery = "SELECT * FROM report WHERE id = ?";
		return jdbcTemplate.queryForObject(sqlQuery, new Object[] { id }, new BasicReportRowMapper());
	}



	@Override
	public void remove(String id) throws DaoException {
		/*
		 * le righe di dettaglio del report sono eliminate dal cascade sulla foreign key
		 */
		String sqlStatement = "DELETE FROM report WHERE id = ?";
		jdbcTemplate.update(sqlStatement, id);
	}

}
