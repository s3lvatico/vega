package org.gmnz.vega.ui.web.report;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;


class ViewHelperCreation extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, 
			ReportManagementBean rmb) {
		RequestProcessingOutcome rpo = new RequestProcessingOutcome();
		try {
			List<Category> categories = vega.getCategoryService().getAllCategoriesWithAllergens();
			req.setAttribute("categories", categories);
			rpo.statusCode = 200;
		} catch (VegaException e) {
			e.printStackTrace();
			rpo.statusCode = 500;
			rpo.errorMessage = "error while retrieving categories";
		}
		return rpo;
	}

}
