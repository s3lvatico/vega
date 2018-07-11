package org.gmnz.vega.ui;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;


public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 4531766441007641102L;

	private Map<String, CategoryManagementBean> navMap;



	@Override
	public void init() {
		navMap = new HashMap<>();

		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Registered categories");
		cmb.setViewName("categories");
		cmb.setAction(Action.GET_ALL);
		navMap.put("getAll", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("New Category Creation");
		cmb.setViewName("categoryManagement");
		cmb.setAction(Action.CREATE);
		navMap.put("create", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Modify Category Name");
		cmb.setViewName("categoryManagement");
		cmb.setAction(Action.MODIFY);
		navMap.put("edit", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Confirm Category Deletion");
		cmb.setViewName("categoryDeletion");
		cmb.setAction(Action.DELETE);
		navMap.put("delete", cmb);

	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());

		CategoryManagementBean cmb = navMap.get(section);
		if (cmb != null) {
			// cmb.setCategoryName(req.getParameter("categoryName"));
			req.setAttribute("catBean", cmb);
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

		CategoryManagementBean cmb = navMap.get(section);
		if (cmb != null) {
			cmb.setCategoryName(req.getParameter("categoryName"));
			req.setAttribute("catBean", cmb);
			req.setAttribute("contextRoot", req.getContextPath());
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
