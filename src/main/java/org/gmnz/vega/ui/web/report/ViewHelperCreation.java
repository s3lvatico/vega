package org.gmnz.vega.ui.web.report;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.domain.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


class ViewHelperCreation extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, HttpServletResponse resp, ReportManagementBean rmb) {
		// TODO Auto-generated method stub
		RequestProcessingOutcome rpo = new RequestProcessingOutcome();
		try {
			List<Category> categories = vega.getCategoryService().getAllCategories();
			req.setAttribute("categories", categories);
		}
		catch (VegaException e) {
			e.printStackTrace();
			rpo.statusCode = 500;
			rpo.errorMessage = "error while retrieving categories";
			return rpo;
		}
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
