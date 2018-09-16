package org.gmnz.vega.ui.web.report;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;

import javax.servlet.http.HttpServletRequest;


abstract class ViewHelperBase {

	protected Vega vega;



	ViewHelperBase() {
		vega = new VegaImpl();
	}



	protected abstract RequestProcessingOutcome processRequest(HttpServletRequest req, ReportManagementBean rmb);

}
