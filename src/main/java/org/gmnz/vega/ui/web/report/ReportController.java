package org.gmnz.vega.ui.web.report;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.service.ReportService;
import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.BaseControllerServlet;


public class ReportController extends BaseControllerServlet {

	private static final long serialVersionUID = -8297293947108342649L;

	private ReportNavigationHandler navigationHandler;



	@Override
	public void init() {
		navigationHandler = new ReportNavigationHandler();
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



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());
		ReportManagementBean rmb = viewMap.get(section);
		if (rmb != null) {
			req.setAttribute("reportBean", rmb);
			Vega vega = new VegaImpl();
			ReportService rs = vega.getReportService();
			Collection<Report> storedReports = null;
			try {
				storedReports = rs.getStoredReports();
			} catch (VegaException e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error while retrieving stored reports");
				return;
			}
			req.setAttribute("reports", storedReports);
			req.setAttribute("contextRoot", req.getContextPath());
			String targetUrl = String.format("/%s.jsp", rmb.getViewName());
			req.getRequestDispatcher(targetUrl).forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "resource not found: " + section);
		}
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());
		ReportManagementBean rmb = viewMap.get(section);
		if (rmb == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "unknown section: <" + section + ">");
			return;
		}

		RequestProcessingOutcome outcome = null;
		switch (rmb.getAction()) {
		case Action.CREATE:
			outcome = new ViewHelperCreation().processRequest(req, resp, rmb);
			break;
		case Action.VIEW_DETAILS:
			outcome = new ViewHelperDetails().processRequest(req, resp, rmb);
			break;
		case Action.DELETE:
			outcome = new ViewHelperDeletion().processRequest(req, resp, rmb);
			break;
		}

		if (outcome.statusCode == HttpServletResponse.SC_OK) {
			req.setAttribute("reportBean", rmb);
			req.setAttribute("contextRoot", req.getContextPath());
			String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", rmb.getViewName());
			req.getRequestDispatcher(targetUrl).forward(req, resp);
		} else {
			resp.sendError(outcome.statusCode, outcome.errorMessage);
		}
	}



	private String findRequestedSection(String requestUrl) {
		int i = requestUrl.lastIndexOf('/');
		String section = requestUrl.substring(i + 1);
		section = VegaUtil.normalizeString(section);
		return section;
	}
}
