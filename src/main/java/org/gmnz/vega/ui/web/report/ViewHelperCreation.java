package org.gmnz.vega.ui.web.report;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.AbstractViewHelper;
import org.gmnz.vega.ui.web.RequestProcessingResult;


class ViewHelperCreation extends AbstractViewHelper {

	@Override
	protected RequestProcessingResult processRequest(HttpServletRequest req, ReportManagementBean rmb) {

		try {
			List<Category> categories = vega.getCategoryService().getAllCategoriesWithAllergens();
			req.setAttribute("categories", categories);
			return RequestProcessingResult.OK();
		} catch (VegaException e) {
			e.printStackTrace();
			return RequestProcessingResult.INTERNAL_SERVER_ERROR("error while retrieving categories");
		}
	}

}
