package org.gmnz.vega.ui.web.category;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.base.VegaUtil;
import org.gmnz.vega.ui.Action;


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
		String newCategoryName = req.getParameter("categoryName");
		if (VegaUtil.stringNullOrEmpty(newCategoryName)) {
			throw new ServletException("invalid category name");
		}
		try {
			switch (action) {
			case Action.CREATE:
				vega.getCategoryService().createCategory(newCategoryName);
				break;
			case Action.MODIFY:
				String categoryId = req.getParameter("categoryId");
				vega.getCategoryService().changeCategoryName(categoryId, newCategoryName);
				break;
			case Action.DELETE:
				vega.getCategoryService().removeCategory(newCategoryName);
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
