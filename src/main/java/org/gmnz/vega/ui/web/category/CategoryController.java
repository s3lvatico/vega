package org.gmnz.vega.ui.web.category;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.ui.web.BaseControllerServlet;


public class CategoryController extends BaseControllerServlet {

	private static final long serialVersionUID = 4531766441007641102L;

	private CategoryRequestHandler requestHandler;



	@Override
	public void init() {
		requestHandler = new CategoryRequestHandler();
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
		requestProcessingResult = requestHandler.handleRequest(requestedSection, req, resp);
	}

}
