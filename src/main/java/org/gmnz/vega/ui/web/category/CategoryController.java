package org.gmnz.vega.ui.web.category;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.ui.web.RequestProcessingResult;


public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 4531766441007641102L;

	private CategoryNavigationHandler navigationHandler;



	@Override
	public void init() {
		navigationHandler = new CategoryNavigationHandler();
	}

//
//	GET .... usare solo per funzioni tipo
//				- getAll
//				- vista di dettaglio per modifica (ma non per la modifica stessa)
//	POST ... tutto il resto
//



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());
		RequestProcessingResult handlerResponse = navigationHandler.handleRequest(section, req, resp);
		int statusCode = handlerResponse.getStatusCode();
		switch (statusCode) {
		case HttpServletResponse.SC_OK:
			req.setAttribute("contextRoot", req.getContextPath());
			String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", handlerResponse.getViewName());
			req.getRequestDispatcher(targetUrl).forward(req, resp);
			return;
		case HttpServletResponse.SC_BAD_REQUEST:
		case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
			resp.sendError(statusCode, handlerResponse.getErrorMessage());
			return;
		}
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());

		// TODO gestione CREATE e DELETE

		/*
		 * questo è simile al vecchio metodo
		 */

//		if (cmb.getAction().equals(Action.CREATE)) {
//			cmb.setCategory(new Category(""));
//			return;
//		}
//		
//		if (cmb.getAction().equals(Action.DELETE)) {
//			String categoryId = req.getParameter("categoryId");
//			Vega v = new VegaImpl();
//			CategoryService categoryService = v.getCategoryService();
//			
//			cmb.setCategory(new Category(""));
//			return;
//		}

	}



	private String findRequestedSection(String requestUrl) {
		int i = requestUrl.lastIndexOf('/');
		// forse c'è da gestire il caso in cui ritorna -1
		return requestUrl.substring(i + 1);
	}
}
