package org.gmnz.vega;


import java.util.Collection;
import java.util.Date;

import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ReportBuildException;
import org.gmnz.vega.domain.ReportBuilder;
import org.gmnz.vega.service.ReportService;
import org.junit.BeforeClass;
import org.junit.Test;


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
	public void createReportNullValues() throws ReportBuildException, VegaException {
		ReportService reportService = vega.getReportService();
		Report r = ReportBuilder.getBuilder().createdOn(new Date()).owner("nazzo").build();
		reportService.createReport(r);
	}

}
