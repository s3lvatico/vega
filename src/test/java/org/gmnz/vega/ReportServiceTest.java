package org.gmnz.vega;


import org.gmnz.vega.domain.Report;
import org.gmnz.vega.service.ReportService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;


/**
 * creato da simone in data 29/08/2018.
 */
public class ReportServiceTest {

	private static Vega vega;



	@BeforeClass
	public static void initApplicationContext() {
		VegaSpringUtil.initSpring();
		vega = VegaFactory.getFactory().buildVega();
	}



	@Test
	public void getStoredReports() throws VegaException {
		Collection<Report> reports = vega.getReportService().getStoredReports();
		System.out.println(reports);
	}



	@Test
	public void createReportNull() throws VegaException {
		ReportService reportService = vega.getReportService();
		reportService.createReport(null);
	}
	@Test
	public void createReportNullValues() throws VegaException {
		ReportService reportService = vega.getReportService();
		Report r = new Report(null, new Date(), "gemini");
		reportService.createReport(r);
	}

}
