package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.repository.DummyRepository;

import java.util.Collection;
import java.util.Date;


/**
 * creato da simone in data 15/07/2018.
 */
public class ReportServiceImpl implements ReportService {

	@Override
	public Collection<Report> getStoredReports() {
		return DummyRepository.getReports();
	}



	@Override
	public Report getReport(String subjectName, Date creationDate) {
		return DummyRepository.getReport(subjectName, creationDate);
	}



	@Override
	public void addReport(Report report) throws VegaException {
		if (DummyRepository.getReport(report.getSubjectName(), report.getCreationDate()) != null) {
			throw new VegaException("report already existing");
		} else {
			DummyRepository.addReport(report);
		}

	}
}
