package org.gmnz.vega.ui;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;


public class CategoryExecution extends HttpServlet {

	private static final long serialVersionUID = -4187200629703344994L;

	private Vega vega;



	@Override
	public void init() throws ServletException {
		vega = new VegaImpl();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null || action.isEmpty()) {
			throw new ServletException("no action specified");
		}
		System.out.format("action requested: <%s>%n", action);
		executeAction(action, req, resp);
	}



	private void executeAction(String action, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String targetCategoryName = req.getParameter("categoryName");
		if (targetCategoryName == null || targetCategoryName.isEmpty()) {
			throw new ServletException("invalid category name");
		}
		switch (action) {
		case Action.CREATE:
			System.out.format("hai scelto: <%s> [%s]%n", action, targetCategoryName);
			vega.createCategory(targetCategoryName);
			break;
		case Action.MODIFY:
			System.out.println("hai scelto: " + action);
			String oldCategoryName = req.getParameter("oldCategoryName");
			System.out.format("Rinomina [%s] --> [%s]%n", oldCategoryName, targetCategoryName);
			vega.renameCategory(oldCategoryName, targetCategoryName);
			break;
		case Action.DELETE:
			System.out.format("hai scelto: <%s> [%s]%n", action, targetCategoryName);
			vega.removeCategory(targetCategoryName);
			break;
		default:
			throw new ServletException("invalid action specified");
		}
		req.getRequestDispatcher("/categories.jsp").forward(req, resp);
	}
}