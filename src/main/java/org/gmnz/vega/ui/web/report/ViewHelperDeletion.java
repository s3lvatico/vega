package org.gmnz.vega.ui.web.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Report;


class ViewHelperDeletion extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, ReportManagementBean rmb) {
		RequestProcessingOutcome outcome = new RequestProcessingOutcome();
		String reportId = req.getParameter("reportId");
		if (VegaUtil.stringNullOrEmpty(reportId)) {
			outcome.statusCode = HttpServletResponse.SC_BAD_REQUEST;
			outcome.errorMessage = "no report id specified";
			return outcome;
		}

		try {
			Report r = vega.getReportService().getReportSummaryById(reportId);
			if (r != null) {
				req.setAttribute("subjectName", r.getSubjectName());
				req.setAttribute("creationDate", r.getCreationDate());
				req.setAttribute("reportId", reportId);
			} else {
				outcome.statusCode = HttpServletResponse.SC_NOT_FOUND;
				outcome.errorMessage = "no report found";
				return outcome;
			}
		} catch (VegaException e) {
			e.printStackTrace();
			outcome.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			outcome.errorMessage = "error while retrieving report";
			return outcome;
		}

		outcome.statusCode = 200;
		return outcome;
	}

}
