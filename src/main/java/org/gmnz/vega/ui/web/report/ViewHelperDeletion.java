package org.gmnz.vega.ui.web.report;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.ui.web.RequestProcessingResult;

import javax.servlet.http.HttpServletRequest;


class ViewHelperDeletion extends ViewHelperBase {

	@Override
	protected RequestProcessingResult processRequest(HttpServletRequest req, ReportManagementBean rmb) {
//		RequestProcessingOutcome outcome = new RequestProcessingOutcome();
		String reportId = req.getParameter("reportId");
		if (VegaUtil.stringNullOrEmpty(reportId)) {
			return RequestProcessingResult.BAD_REQUEST("no report id specified");
//			outcome.statusCode = HttpServletResponse.SC_BAD_REQUEST;
//			outcome.errorMessage = "no report id specified";
//			return outcome;
		}

		try {
			Report r = vega.getReportService().getReportSummaryById(reportId);
			if (r != null) {
				req.setAttribute("subjectName", r.getSubjectName());
				req.setAttribute("creationDate", r.getCreationDate());
				req.setAttribute("reportId", reportId);
			} else {
				return RequestProcessingResult.NOT_FOUND("no report found");
//				outcome.statusCode = HttpServletResponse.SC_NOT_FOUND;
//				outcome.errorMessage = "no report found";
//				return outcome;
			}
		} catch (VegaException e) {
			e.printStackTrace();
			return RequestProcessingResult.INTERNAL_SERVER_ERROR("error while retrieving report");
//			outcome.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
//			outcome.errorMessage = "error while retrieving report";
//			return outcome;
		}

		return RequestProcessingResult.OK();

//		outcome.statusCode = 200;
//		return outcome;
	}

}
