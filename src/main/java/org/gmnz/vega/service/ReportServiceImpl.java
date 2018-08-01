package org.gmnz.vega.service;


import java.util.Collection;
import java.util.Date;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.base.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.repository.DummyRepository;


/**
 * creato da simone in data 15/07/2018.
 */
public class ReportServiceImpl implements ReportService {

	// TODO ReportServiceImpl referenziare il dao, mai direttamente il repository

	@Override
	public Collection<Report> getStoredReports() {
		return DummyRepository.getReports();
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
	public void addReport(Report report) throws VegaException {
		if (DummyRepository.getReport(report.getSubjectName(), report.getCreationDate()) != null) {
			throw new VegaException("report already existing");
		} else {
			DummyRepository.addReport(report);
		}

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
