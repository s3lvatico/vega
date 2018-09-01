package org.gmnz.vega.ui.web.report;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.RequestProcessingResult;


class ViewHelperCreation extends ViewHelperBase {

	@Override
	protected RequestProcessingResult processRequest(HttpServletRequest req, ReportManagementBean rmb) {
		// RequestProcessingResult rpr = new RequestProcessingResult()
//		int statusCode;
//		String errorMessage;
		try {
			List<Category> categories = vega.getCategoryService().getAllCategoriesWithAllergens();
			req.setAttribute("categories", categories);
			// rpo.statusCode = 200;
//			statusCode = 200;
			return RequestProcessingResult.OK();
		} catch (VegaException e) {
			e.printStackTrace();
			return RequestProcessingResult.INTERNAL_SERVER_ERROR("error while retrieving categories");
//			statusCode = 500;
//			errorMessage = "error while retrieving categories";
//			rpo.statusCode = 500;
//			rpo.errorMessage = "error while retrieving categories";
		}
		//return rpo;

	}

}
