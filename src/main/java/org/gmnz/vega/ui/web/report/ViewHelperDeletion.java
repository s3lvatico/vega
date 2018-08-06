package org.gmnz.vega.ui.web.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


class ViewHelperDeletion extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, HttpServletResponse resp,
			ReportManagementBean rmb) {
		// TODO - ViewHelperDeletion processRequest - fare

//		String reportId = req.getParameter("reportId");
//		Report r = vega.getReportService().getReport(reportId);
//		req.setAttribute("subjectName", r.getSubjectName());
//		req.setAttribute("creationDate", r.getCreationDate());
//		req.setAttribute("reportId", reportId);

		RequestProcessingOutcome outcome = new RequestProcessingOutcome();
		outcome.statusCode = 404;
		outcome.errorMessage = "not yet implemented";
		return outcome;
	}

}
