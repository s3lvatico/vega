package org.gmnz.vega.ui.web;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaFactory;
import org.gmnz.vega.ui.web.RequestProcessingResult;
import org.gmnz.vega.ui.web.report.ReportManagementBean;

import javax.servlet.http.HttpServletRequest;


public abstract class AbstractViewHelper {

	protected Vega vega;



	protected AbstractViewHelper() {
		vega = VegaFactory.getFactory().buildVega();
	}



	protected abstract RequestProcessingResult processRequest(HttpServletRequest req, ReportManagementBean rmb);

}
