package org.gmnz.vega.ui.web.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;


abstract class ViewHelperBase {

	protected Vega vega;



	ViewHelperBase() {
		vega = new VegaImpl();
	}



	protected abstract RequestProcessingOutcome processRequest(HttpServletRequest req, HttpServletResponse resp,
			ReportManagementBean rmb);
}
