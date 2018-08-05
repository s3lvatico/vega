package org.gmnz.vega.ui.web.allergen;


import org.gmnz.vega.ui.web.BaseViewResolverServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AllergenController extends BaseViewResolverServlet {


	private static final long serialVersionUID = 4409051097972758443L;

	private AllergenNavigationHandler navigationHandler;



	@Override
	public void init() {
		navigationHandler = new AllergenNavigationHandler();
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
