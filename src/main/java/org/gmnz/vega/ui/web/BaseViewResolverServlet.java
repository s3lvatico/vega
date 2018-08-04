package org.gmnz.vega.ui.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BaseViewResolverServlet extends HttpServlet {

	private HttpServletRequest request;
	private HttpServletResponse response;


	protected RequestProcessingResult requestProcessingResult;
	protected String requestedSection;



	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.request = req;
		this.response = resp;
		requestedSection = findRequestedSection(req);
		super.service(req, resp);
		dispatchToView();
	}



	private void dispatchToView() throws ServletException, IOException {
		int statusCode = requestProcessingResult.getStatusCode();
		switch (statusCode) {
			case HttpServletResponse.SC_OK:
				request.setAttribute("contextRoot", request.getContextPath());
				String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", requestProcessingResult.getViewName());
				request.getRequestDispatcher(targetUrl).forward(request, response);
				return;
			case HttpServletResponse.SC_BAD_REQUEST:
			case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
				response.sendError(statusCode, requestProcessingResult.getErrorMessage());
				return;
		}
	}



	private String findRequestedSection(HttpServletRequest req) {
		String requestUrl = req.getRequestURL().toString();
		int i = requestUrl.lastIndexOf('/');
		return requestUrl.substring(i + 1);
	}

}
