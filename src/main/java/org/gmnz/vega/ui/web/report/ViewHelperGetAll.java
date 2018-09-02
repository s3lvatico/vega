package org.gmnz.vega.ui.web.report;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.service.ReportService;
import org.gmnz.vega.ui.web.AbstractViewHelper;
import org.gmnz.vega.ui.web.RequestProcessingResult;


class ViewHelperGetAll extends AbstractViewHelper {

	@Override
	protected RequestProcessingResult processRequest(HttpServletRequest req, ReportManagementBean rmb) {
		ReportService reportService = vega.getReportService();
		Collection<Report> storedReports = null;
		try {
			storedReports = reportService.getStoredReports();
			req.setAttribute("reports", storedReports);
			return RequestProcessingResult.OK();
		} catch (VegaException e) {
			e.printStackTrace();
			return RequestProcessingResult.INTERNAL_SERVER_ERROR("error while retrieving stored reports");
		}
	}

}
