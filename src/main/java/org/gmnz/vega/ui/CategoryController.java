package org.gmnz.vega.ui;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CategoryController extends HttpServlet {

	private Map<String, CategoryManagementBean> navMap;



	@Override
	public void init() throws ServletException {
		navMap = new HashMap<>();
		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("New Category Creation");
		navMap.put("create", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Modify Category Name");
		navMap.put("edit", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Confirm Category Deletion");
		navMap.put("delete", cmb);

	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.format("request URL: <%s>%n", req.getRequestURL());
		System.out.format("request URI: <%s>%n", req.getRequestURI());
		String section = findRequestedSection(req.getRequestURL().toString());
		System.out.println("section requested: " + section);

		CategoryManagementBean cmb = navMap.get(section);
		if (cmb != null) {
			// TODO dispatch
			req.getRequestDispatcher("/categories.jsp").forward(req, resp);
		} else {
			System.err.println("wrong path requested");
			// TODO rimanda ad una pagina di errore
		}
	}



	private String findRequestedSection(String requestUrl) {
		int i = requestUrl.lastIndexOf('/');
		// forse c'è da gestire il caso in cui ritorna -1
		return requestUrl.substring(i + 1);
	}
}
