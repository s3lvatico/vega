package org.gmnz.vega.ui.web.allergen;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.base.VegaUtil;
import org.gmnz.vega.ui.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AllergenExecution extends HttpServlet {

	private static final long serialVersionUID = -4187200629703344994L;

	private Vega vega;



	@Override
	public void init() {
		vega = new VegaImpl();
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (VegaUtil.stringNullOrEmpty(action)) {
			String errorMessage = "no action specified";
			req.setAttribute("errorMessage", errorMessage);
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMessage);
			return;
		}
		executeAction(action, req, resp);
	}



	private void executeAction(String action, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		String targetAllergenName = req.getParameter("allergenName");
//		String targetCategoryName = req.getParameter("categoryName");
//		if (VegaUtil.stringNullOrEmpty(targetAllergenName)) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
		try {
			switch (action) {
				case Action.CREATE:
					String newAllergenName = req.getParameter("allergenName");
					String categoryId = req.getParameter("categoryId");
					if (VegaUtil.stringNullOrEmpty(newAllergenName)
							|| VegaUtil.stringNullOrEmpty(categoryId)) {
						resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
						return;
					}
					createNewAllergen(newAllergenName, categoryId);
					break;
				case Action.MODIFY:
					// TODO Allergen MODIFY
//					HttpSession session = req.getSession();
//					String trackingId = req.getParameter("trackingId");
//					Allergen originalAllergen = (Allergen) session.getAttribute(trackingId);
//					if (originalAllergen == null) {
//						resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "no session content");
//						return;
//					}
//					session.setAttribute("trackingId", null);
//					vega.getAllergenService().modifyAllergen(originalAllergen, newAllergenName, targetCategoryName);
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "not yet implemented");
					return;
//					break;
				case Action.DELETE:
					// TODO Allergen DELETE
//					vega.getAllergenService().removeAllergen(allergenId);
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "not yet implemented");
					return;
//					break;
				default:
					throw new ServletException("unrecognized action specified");
			}
		} catch (VegaException ve) {
			// TODO meglio gestire gli errori applicativi con gli status code http
			String errorMessage = String.format("exception thrown while executing action -- %s :: %s",
					ve.getClass().getName(), ve.getMessage());
			throw new ServletException(errorMessage, ve);
		}
		req.getRequestDispatcher("/allergen/getAll").forward(req, resp);
	}



	private void createNewAllergen(String newAllergenName, String categoryId) throws VegaException {
		vega.getAllergenService().createAllergen(newAllergenName, categoryId);
	}

}
