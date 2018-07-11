package org.gmnz.vega.ui;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AllergenController extends HttpServlet {

	private static final long serialVersionUID = 4531766441007641102L;

	private Map<String, AllergenManagementBean> navMap;



	@Override
	public void init() {
		navMap = new HashMap<>();

		AllergenManagementBean cmb = new AllergenManagementBean();
		cmb.setOperationLabel("Registered allergens");
		cmb.setViewName("allergens");
		cmb.setAction(Action.GET_ALL);
		navMap.put("getAll", cmb);

		cmb = new AllergenManagementBean();
		cmb.setOperationLabel("New Allergen Creation");
		cmb.setViewName("allergenManagement");
		cmb.setAction(Action.CREATE);
		navMap.put("create", cmb);

		cmb = new AllergenManagementBean();
		cmb.setOperationLabel("Modify Allergen");
		cmb.setViewName("allergenManagement");
		cmb.setAction(Action.MODIFY);
		navMap.put("edit", cmb);

		cmb = new AllergenManagementBean();
		cmb.setOperationLabel("Confirm Allergen Deletion");
		cmb.setViewName("allergenDeletion");
		cmb.setAction(Action.DELETE);
		navMap.put("delete", cmb);

	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());

		AllergenManagementBean cmb = navMap.get(section);
		if (cmb != null) {
			req.setAttribute("allergenBean", cmb);
			Vega vega = new VegaImpl();
			req.setAttribute("vega", vega);
			req.setAttribute("contextRoot", req.getContextPath());
			String targetUrl = String.format("/%s.jsp", cmb.getViewName());
			req.getRequestDispatcher(targetUrl).forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "resource not found: " + section);
		}

	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());
		AllergenManagementBean cmb = navMap.get(section);
		if (cmb != null) {
			cmb.setAllergenName(req.getParameter("allergenName"));
			req.setAttribute("allergenBean", cmb);
			req.setAttribute("contextRoot", req.getContextPath());
			List<Category> categories = new VegaImpl().getCategoryService().getAllCategories();
			req.setAttribute("categories", categories);
			String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", cmb.getViewName());
			req.getRequestDispatcher(targetUrl).forward(req, resp);
		} else {
			throw new ServletException("wrong path specified: <" + section + ">");
		}
	}



	private String findRequestedSection(String requestUrl) {
		int i = requestUrl.lastIndexOf('/');
		// forse c'è da gestire il caso in cui ritorna -1
		return requestUrl.substring(i + 1);
	}
}
