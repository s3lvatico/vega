package org.gmnz.vega.ui.web.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class ViewHelperCreation extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, HttpServletResponse resp, ReportManagementBean rmb) {
		// TODO Auto-generated method stub
		
		// recupero categorie
//		try {
//			List<Category> categories = vega.getCategoryService().getAllCategories();
//			req.setAttribute("categories", categories);
//		} catch (VegaException e) {
//			e.printStackTrace();
//			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
//					"exception thrown while retrieving the categories");
//			return;
//		}

		return null;
	}

}
