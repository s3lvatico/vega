package org.gmnz.vega.ui;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;


public class CategoryExecution extends HttpServlet {

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
		// System.out.format("action requested: <%s>%n", action);
		executeAction(action, req, resp);
	}



	private void executeAction(String action, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String targetCategoryName = req.getParameter("categoryName");
		if (targetCategoryName == null || targetCategoryName.isEmpty()) {
			throw new ServletException("invalid category name");
		}
		try {
			switch (action) {
			case Action.CREATE:
				vega.getCategoryService().createCategory(targetCategoryName);
				break;
			case Action.MODIFY:
				String oldCategoryName = req.getParameter("oldCategoryName");
				vega.getCategoryService().renameCategory(oldCategoryName, targetCategoryName);
				break;
			case Action.DELETE:
				vega.getCategoryService().removeCategory(targetCategoryName);
				break;
			default:
				throw new ServletException("invalid action specified");
			}
		} catch (VegaException ve) {
			String errorMessage = String.format("exception thrown while executing action -- %s :: %s",
					ve.getClass().getName(), ve.getMessage());
			throw new ServletException(errorMessage, ve);
		}
		req.getRequestDispatcher("/category/getAll").forward(req, resp);
	}
}
