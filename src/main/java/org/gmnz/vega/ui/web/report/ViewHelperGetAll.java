package org.gmnz.vega.ui.web.report;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.service.ReportService;


class ViewHelperGetAll extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, ReportManagementBean rmb) {
		RequestProcessingOutcome rpo = new RequestProcessingOutcome();
		ReportService reportService = vega.getReportService();
		Collection<Report> storedReports = null;
		try {
			storedReports = reportService.getStoredReports();
			req.setAttribute("reports", storedReports);
			rpo.statusCode = HttpServletResponse.SC_OK;
		} catch (VegaException e) {
			e.printStackTrace();
			rpo.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			rpo.errorMessage = "error while retrieving stored reports";
		}
		return rpo;
	}

}
