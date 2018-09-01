package org.gmnz.vega.ui.web.report;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaFactory;
import org.gmnz.vega.ui.web.RequestProcessingResult;

import javax.servlet.http.HttpServletRequest;


abstract class ViewHelperBase {

	protected Vega vega;



	ViewHelperBase() {
		vega = VegaFactory.getFactory().buildVega();
	}



	protected abstract RequestProcessingResult processRequest(HttpServletRequest req, ReportManagementBean rmb);

}
