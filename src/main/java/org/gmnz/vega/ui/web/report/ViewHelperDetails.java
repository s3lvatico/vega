package org.gmnz.vega.ui.web.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


class ViewHelperDetails extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, HttpServletResponse resp,
			ReportManagementBean rmb) {
		// TODO - ViewHelperDetails processRequest - fare

		/* parti da qui */
//		Vega vega = new VegaImpl();
//		String reportId = req.getParameter("reportId");
//		Report r = vega.getReportService().getReport(reportId);

//		ViewReportData viewReportData = prepareReportData(r);
//		req.setAttribute("reportData", viewReportData);
		// ---

		RequestProcessingOutcome outcome = new RequestProcessingOutcome();
		outcome.statusCode = 404;
		outcome.errorMessage = "not yet implemented";
		return outcome;
	}

}
