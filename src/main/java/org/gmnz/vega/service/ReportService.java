package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Report;

import java.util.Collection;
import java.util.Date;


/**
 * creato da simone in data 15/07/2018.
 */
public interface ReportService {

	Collection<Report> getStoredReports();

	Report getReport(String subjectName, Date creationDate);

	void addReport(Report report) throws VegaException;

}
