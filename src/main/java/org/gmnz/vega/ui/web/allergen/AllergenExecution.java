package org.gmnz.vega.ui.web.allergen;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.ExecutionServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AllergenExecution extends ExecutionServlet {

	private static final long serialVersionUID = -4187200629703344994L;



	protected void executeAction(String action, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			switch (action) {
				case Action.CREATE:
					String newAllergenName = req.getParameter("allergenName");
					String categoryId = req.getParameter("categoryId");
					if (VegaUtil.stringNullOrEmpty(newAllergenName) || VegaUtil.stringNullOrEmpty(categoryId)) {
						resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
						return;
					}
					vega.getAllergenService().createAllergen(newAllergenName, categoryId);
					break;
				case Action.MODIFY:
					Allergen initialAllergen = (Allergen) req.getSession().getAttribute("allergen");
					if (initialAllergen == null) {
						resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "no original allergen stored in session");
						return;
					}
					req.getSession().removeAttribute("allergen");

					String paramAllergenName = req.getParameter("allergenName");
					String paramCategoryId = req.getParameter("categoryId");

					if (VegaUtil.stringNullOrEmpty(paramAllergenName) || VegaUtil.stringNullOrEmpty(paramCategoryId)) {
						resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "empty values for parameters are not allowed");
						return;
					}

					vega.getAllergenService().modifyAllergen(initialAllergen, paramAllergenName, paramCategoryId);

					break;
				case Action.DELETE:
					String paramAllergenId = req.getParameter("allergenId");
					if (VegaUtil.stringNullOrEmpty(paramAllergenId)) {
						resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "no allergen id specified");
						return;
					}
					vega.getAllergenService().removeAllergen(paramAllergenId);
					break;
				default:
					throw new ServletException("unrecognized action specified");
			}
		} catch (VegaException e) {
			String errorMessage = String.format("exception thrown while executing action [%s] -- %s :: %s", action,
					e.getClass().getName(), e.getMessage());
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
		}
		req.getRequestDispatcher("/app/allergen/getAll").forward(req, resp);
	}


}
