package org.gmnz.vega.ui.web.report;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.ui.web.AbstractViewHelper;
import org.gmnz.vega.ui.web.RequestProcessingResult;

import javax.servlet.http.HttpServletRequest;


class ViewHelperDeletion extends AbstractViewHelper {

	@Override
	protected RequestProcessingResult processRequest(HttpServletRequest req, ReportManagementBean rmb) {
		String reportId = req.getParameter("reportId");
		if (VegaUtil.stringNullOrEmpty(reportId)) {
			return RequestProcessingResult.BAD_REQUEST("no report id specified");
		}

		try {
			Report r = vega.getReportService().getReportSummaryById(reportId);
			if (r != null) {
				req.setAttribute("subjectName", r.getSubjectName());
				req.setAttribute("creationDate", r.getCreationDate());
				req.setAttribute("reportId", reportId);
			} else {
				return RequestProcessingResult.NOT_FOUND("no report found");
			}
		} catch (VegaException e) {
			e.printStackTrace();
			return RequestProcessingResult.INTERNAL_SERVER_ERROR("error while retrieving report");
		}

		return RequestProcessingResult.OK();
	}

}
