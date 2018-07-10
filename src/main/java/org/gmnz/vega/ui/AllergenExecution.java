package org.gmnz.vega.ui;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null || action.isEmpty()) {
			throw new ServletException("no action specified");
		}
		//System.out.format("action requested: <%s>%n", action);
		executeAction(action, req, resp);
	}



	private void executeAction(String action, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String targetAllergenName = req.getParameter("allergenName");
		if (targetAllergenName == null || targetAllergenName.isEmpty()) {
			throw new ServletException("invalid allergen name");
		}
		try {
			switch (action) {
			case Action.CREATE:
				//System.out.format("hai scelto: <%s> [%s]%n", action, targetCategoryName);
				vega.getAllergenService().createAllergen(targetAllergenName);
				break;
			case Action.MODIFY:
				//System.out.println("hai scelto: " + action);
				String oldAllergenName = req.getParameter("oldAllergenName");
				//System.out.format("Rinomina [%s] --> [%s]%n", oldCategoryName, targetCategoryName);
				vega.getAllergenService().renameAllergen(oldAllergenName, targetAllergenName);
				break;
			case Action.DELETE:
				//System.out.format("hai scelto: <%s> [%s]%n", action, targetCategoryName);
				vega.getAllergenService().removeAllergen(targetAllergenName);
				break;
			default:
				throw new ServletException("invalid action specified");
			}
		} catch (VegaException ve) {
			String errorMessage = String.format("exception thrown while executing action -- %s :: %s", ve.getClass().getName(), ve.getMessage());
			throw new ServletException(errorMessage, ve);
		}
		req.getRequestDispatcher("/category/getAll").forward(req, resp);
	}
}
