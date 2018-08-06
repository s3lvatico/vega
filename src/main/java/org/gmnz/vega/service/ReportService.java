package org.gmnz.vega.service;


import java.util.Collection;
import java.util.Date;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Report;


/**
 * creato da simone in data 15/07/2018.
 */
public interface ReportService {

	Collection<Report> getStoredReports() throws VegaException;



	Report getReport(String subjectName, Date creationDate);



	Report getReport(String id);



	void addReport(Report report) throws VegaException;



	void removeReport(String id);

}
