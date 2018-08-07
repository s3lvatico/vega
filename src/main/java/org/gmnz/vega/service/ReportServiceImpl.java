package org.gmnz.vega.service;


import java.util.Collection;
import java.util.Date;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.base.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;
import org.gmnz.vega.repository.DummyRepository;
import org.gmnz.vega.repository.ReportDao;


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
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("getStoredReports data access error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	@Override
	public void createReport(Report report) throws VegaException {
		ReportDao dao = null;
		try {
			dao = DaoFactory.getInstance().createReportDao();
			dao.createReport(report);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new VegaException("createReport data access error", e);
		} finally {
			finalizeDao(dao);
		}
	}



	@Override
	public Report getReport(String subjectName, Date creationDate) {
		return DummyRepository.getReport(subjectName, creationDate);
	}



	@Override
	public Report getReport(String id) {
		return DummyRepository.getReportById(id);
	}



	@Override
	public void removeReport(String id) {
		if (!VegaUtil.stringNullOrEmpty(id)) {
			Report r = DummyRepository.getReportById(id);
			if (r != null) {
				DummyRepository.removeReport(r);
			}
		}
	}
}
