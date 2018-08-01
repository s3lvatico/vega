package org.gmnz.vega.ui.web.allergen;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.base.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.ui.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AllergenExecution extends HttpServlet {

	private static final long serialVersionUID = -4187200629703344994L;

	private Vega vega;



	@Override
	public void init() {
		vega = new VegaImpl();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (VegaUtil.stringNullOrEmpty(action)) {
			throw new ServletException("no action specified");
		}
		executeAction(action, req, resp);
	}



	private void executeAction(String action, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String targetAllergenName = req.getParameter("allergenName");
		String targetCategoryName = req.getParameter("categoryName");
		if (VegaUtil.stringNullOrEmpty(targetAllergenName)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		try {
			switch (action) {
			case Action.CREATE:
				if (VegaUtil.stringNullOrEmpty(targetCategoryName)) {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
				createNewAllergen(targetAllergenName, targetCategoryName);
				break;
			case Action.MODIFY:
				HttpSession session = req.getSession();
				String trackingId = req.getParameter("trackingId");
				Allergen originalAllergen = (Allergen) session.getAttribute(trackingId);
				if (originalAllergen == null) {
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "no session content");
					return;
				}
				session.setAttribute("trackingId", null);
				vega.getAllergenService().modifyAllergen(originalAllergen, targetAllergenName, targetCategoryName);
				break;
			case Action.DELETE:
				vega.getAllergenService().removeAllergen(targetAllergenName);
				break;
			default:
				throw new ServletException("invalid action specified");
			}
		} catch (VegaException ve) {
			String errorMessage = String.format("exception thrown while executing action -- %s :: %s",
					ve.getClass().getName(), ve.getMessage());
			throw new ServletException(errorMessage, ve);
		}
		req.getRequestDispatcher("/allergen/getAll").forward(req, resp);
	}



	private void createNewAllergen(String name, String categoryName) throws VegaException {
		vega.getAllergenService().createAllergen(name, categoryName);
	}

}
