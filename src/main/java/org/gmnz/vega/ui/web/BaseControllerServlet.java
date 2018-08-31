package org.gmnz.vega.ui.web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 5139974582329075960L;

	protected RequestProcessingResult requestProcessingResult;
	protected String requestedSection;



	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestedSection = findRequestedSection(req);
		// invoca il metodo corretto doGet(), doPost(), ...
		super.service(req, resp);
		dispatchToView(req, resp);
	}



	private String findRequestedSection(HttpServletRequest req) {
		String requestUrl = req.getRequestURL().toString();
		int i = requestUrl.lastIndexOf('/');
		return requestUrl.substring(i + 1);
	}



	private void dispatchToView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int statusCode = requestProcessingResult.getStatusCode();
		switch (statusCode) {
		case HttpServletResponse.SC_OK:
			req.setAttribute("contextRoot", req.getContextPath());
			String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", requestProcessingResult.getViewName());
			req.getRequestDispatcher(targetUrl).forward(req, resp);
			return;
		case HttpServletResponse.SC_BAD_REQUEST:
		case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
			resp.sendError(statusCode, requestProcessingResult.getErrorMessage());
			return;
		}
	}

}
