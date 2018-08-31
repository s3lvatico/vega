package org.gmnz.vega.service;


import java.util.Collection;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.ReportDao;


/**
 * creato da simone in data 15/07/2018.
 */
public class ReportServiceImpl extends BasicServiceBean implements ReportService {

	protected ReportServiceImpl(DaoFactory daoFactory) {
		super(daoFactory);
	}



	@Override
	public Collection<Report> getStoredReports() throws VegaException {
		ReportDao dao = daoFactory.createReportDao();
		try {
			return dao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getStoredReports data access error", e);
		}
	}



	@Override
	public Report getReportById(String id) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(id)) {
			return null;
		}
		ReportDao dao = daoFactory.createReportDao();
		try {
			return dao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getReportById service error", e);
		}
	}



	@Override
	public Report getReportSummaryById(String id) throws VegaException {
		if (VegaUtil.stringNullOrEmpty(id)) {
			return null;
		}
		ReportDao dao = daoFactory.createReportDao();
		try {
			return dao.getSummaryById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getReportSummaryById service error", e);
		}
	}



	@Override
	public void createReport(Report report) throws VegaException {
		if (report != null) {
			ReportDao dao = daoFactory.createReportDao();
			try {
				dao.createReport(report);
			} catch (DaoException e) {
				e.printStackTrace();
				throw new VegaException("createReport service error", e);
			}
		} else {
			System.err.println("warning: null report specified");
		}
	}



	@Override
	public void removeReport(String id) throws VegaException {
		ReportDao dao = daoFactory.createReportDao();
		try {
			dao.remove(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("removeReport service error", e);
		}
	}

}
