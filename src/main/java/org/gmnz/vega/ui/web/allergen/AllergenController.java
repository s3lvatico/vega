package org.gmnz.vega.ui.web.allergen;


import org.gmnz.vega.ui.web.BaseViewResolverServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AllergenController extends BaseViewResolverServlet {

	private static final long serialVersionUID = 4531766441007641102L;


	private AllergenNavigationHandler navigationHandler;



	@Override
	public void init() {
		navigationHandler = new AllergenNavigationHandler();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String section = findRequestedSection(req.getRequestURL().toString());
//
//		AllergenManagementBean cmb = navMap.get(section);
//		if (cmb != null) {
//			req.setAttribute("allergenBean", cmb);
//			Vega vega = new VegaImpl();
//			req.setAttribute("vega", vega);
//			req.setAttribute("contextRoot", req.getContextPath());
//			String targetUrl = String.format("/%s.jsp", cmb.getViewName());
//			req.getRequestDispatcher(targetUrl).forward(req, resp);
//		} else {
//			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "resource not found: " + section);
//		}

		processRequest(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String section = findRequestedSection(req.getRequestURL().toString());
//		AllergenManagementBean cmb = navMap.get(section);
//		if (cmb != null) {
//			String allergenName = req.getParameter("allergenName");
//
//			cmb.setAllergenName(allergenName);
//			req.setAttribute("allergenBean", cmb);
//
//			Vega vega = new VegaImpl();
//			req.setAttribute("contextRoot", req.getContextPath());
//
//			// recupero le categorie
//			try {
//				List<Category> categories = vega.getCategoryService().getAllCategories();
//				req.setAttribute("categories", categories);
//			} catch (VegaException e) {
//				e.printStackTrace();
//				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
//						"exception thrown while retrieving the categories");
//				return;
//			}
//
//			// mantengo in sessione l'oggetto allergene originale in modo da determinare
//			// eventuali modifiche radicali
//
//			AllergenService allergenService = vega.getAllergenService();
//			Allergen originalAllergen = allergenService.get(allergenName);
//			if (originalAllergen != null) {
//				String uuid = UUID.randomUUID().toString();
//				req.getSession().setAttribute(uuid, originalAllergen);
//				req.setAttribute("trackingId", uuid);
//				req.setAttribute("initialAllergenCategoryName", originalAllergen.getCategory().getName());
//			}
//			//
//
//			String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", cmb.getViewName());
//			req.getRequestDispatcher(targetUrl).forward(req, resp);
//		} else {
//			throw new ServletException("wrong path specified: <" + section + ">");
//		}
		processRequest(req, resp);
	}


//	private void serviceRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestProcessingResult handlerResponse = navigationHandler.handleRequest(section, req, resp);
//		int statusCode = handlerResponse.getStatusCode();
//		switch (statusCode) {
//			case HttpServletResponse.SC_OK:
//				req.setAttribute("contextRoot", req.getContextPath());
//				String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", handlerResponse.getViewName());
//				req.getRequestDispatcher(targetUrl).forward(req, resp);
//				return;
//			case HttpServletResponse.SC_BAD_REQUEST:
//			case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
//				resp.sendError(statusCode, handlerResponse.getErrorMessage());
//				return;
//		}
//	}



	private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		requestProcessingResult = navigationHandler.handleRequest(requestedSection, req, resp);
	}


}
