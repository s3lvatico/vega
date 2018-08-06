package org.gmnz.vega.ui.web.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


class ViewHelperDeletion extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, HttpServletResponse resp,
			ReportManagementBean rmb) {
		// TODO - RequestProcessingOutcome processRequest - fare
		RequestProcessingOutcome outcome = new RequestProcessingOutcome();
		outcome.statusCode = 404;
		outcome.errorMessage = "not yet implemented";
		return outcome;
	}

}
