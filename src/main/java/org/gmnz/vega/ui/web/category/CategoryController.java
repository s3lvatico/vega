package org.gmnz.vega.ui.web.category;


import org.gmnz.vega.ui.web.BaseControllerServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CategoryController extends BaseControllerServlet {

	private static final long serialVersionUID = 4531766441007641102L;

	private CategoryNavigationHandler navigationHandler;



	@Override
	public void init() {
		navigationHandler = new CategoryNavigationHandler();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}



	private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		requestProcessingResult = navigationHandler.handleRequest(requestedSection, req, resp);
	}

}
