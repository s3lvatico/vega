package org.gmnz.vega.ui;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.AllergenService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
			String allergenName = req.getParameter("allergenName");
			cmb.setAllergenName(allergenName);
			req.setAttribute("allergenBean", cmb);
			req.setAttribute("contextRoot", req.getContextPath());
			List<Category> categories = new VegaImpl().getCategoryService().getAllCategories();
			req.setAttribute("categories", categories);
			// mantengo in sessione l'oggetto allergene originale in modo da determinare eventuali modifiche radicali
			Vega vega = new VegaImpl();
			AllergenService allergenService = vega.getAllergenService();
			Allergen originalAllergen = allergenService.get(allergenName);
			String uuid = UUID.randomUUID().toString();
			req.getSession().setAttribute(uuid, originalAllergen);
			req.setAttribute("trackingId", uuid);
			//

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
