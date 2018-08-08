package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Report;

import java.util.Collection;
import java.util.Date;


/**
 * creato da simone in data 15/07/2018.
 */
public interface ReportService {

	Collection<Report> getStoredReports() throws VegaException;


	Report getReportSummaryById(String id) throws VegaException;


	Report getReportById(String id) throws VegaException;


	void createReport(Report report) throws VegaException;


	void removeReport(String id) throws VegaException;

}
