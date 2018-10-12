package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.ReportDao;

import java.util.Collection;


/**
 * creato da simone in data 15/07/2018.
 */
public class ReportServiceImpl extends BasicServiceBean implements ReportService {

	@Override
	public Collection<Report> getStoredReports() throws VegaException {
		ReportDao dao = null;
		try {
			dao = DaoFactory.getInstance().createReportDao();
			Collection<Report> reports = dao.findAll();
			return reports;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getStoredReports data access error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public Report getReportById(String id) throws VegaException {
		ReportDao dao = null;
		try {
			dao = DaoFactory.getInstance().createReportDao();
			return dao.findById(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getReportById service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public Report getReportSummaryById(String id) throws VegaException {
		ReportDao dao = null;
		try {
			dao = DaoFactory.getInstance().createReportDao();
			return dao.getSummaryById(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getReportSummaryById service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void createReport(Report report) throws VegaException {
		ReportDao dao = null;
		try {
			dao = DaoFactory.getInstance().createReportDao();
			dao.createReport(report);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createReport service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void removeReport(String id) throws VegaException {
		ReportDao dao = null;
		try {
			dao = DaoFactory.getInstance().createReportDao();
			dao.remove(id);
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("removeReport service error", e);
		}
		finally {
			finalizeDao(dao);
		}
	}



	@Override
	public int countReports() throws VegaException {
		ReportDao reportDao = null;

		try {
			reportDao = DaoFactory.getInstance().createReportDao();
			return reportDao.countReports();
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("countReports service error", e);
		}
		finally {
			finalizeDao(reportDao);
		}
	}

}
