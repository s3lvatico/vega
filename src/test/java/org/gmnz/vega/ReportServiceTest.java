package org.gmnz.vega;


import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ReportBuilder;
import org.gmnz.vega.service.ReportService;
import org.junit.Assert;
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
	public void createReport() throws VegaException {
		ReportService reportService = vega.getReportService();
		Report expected = ReportBuilder.getBuilder().subjectName("Test Subject 01").createdOn(new Date()).owner("gemini")
				.build();
		reportService.createReport(expected);

		Collection<Report> reports = reportService.getStoredReports();

		Assert.assertTrue(reports.contains(expected));

		reportService.removeReport(expected.getId());
	}



	@Test
	public void getReportById() throws VegaException {
		ReportService reportService = vega.getReportService();
		String expectedId = "";
		try {
			Report expected = ReportBuilder.getBuilder().subjectName("Test Subject 01").createdOn(new Date()).owner("gemini")
					.build();
			reportService.createReport(expected);

			expectedId = expected.getId();

			Report actual = reportService.getReportById(expectedId);

			Assert.assertEquals(expected, actual);
		} finally {
			if (!VegaUtil.stringNullOrEmpty(expectedId)) {
				reportService.removeReport(expectedId);
			}
		}
	}



	@Test
	public void getReportByIdNull() throws VegaException {
		ReportService reportService = vega.getReportService();
		Assert.assertNull(reportService.getReportById(null));
	}



	@Test
	public void getReportByIdWrong() throws VegaException {
		ReportService reportService = vega.getReportService();
		Assert.assertNull(reportService.getReportById("wronski"));
	}



	@Test
	public void getReportSummaryByIdNull() throws VegaException {
		ReportService reportService = vega.getReportService();
		Assert.assertNull(reportService.getReportSummaryById(null));
	}



	@Test
	public void getReportSummaryByIdWrong() throws VegaException {
		ReportService reportService = vega.getReportService();
		Assert.assertNull(reportService.getReportSummaryById("wronski"));
	}

}
