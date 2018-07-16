package org.gmnz.vega.ui;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.service.ReportService;


public class ReportController extends HttpServlet {

	private static final long serialVersionUID = -8297293947108342649L;
	
	private Map<String, ReportManagementBean> navMap;



	@Override
	public void init() {
		navMap = new HashMap<>();

		ReportManagementBean cmb = new ReportManagementBean();
		cmb.setOperationLabel("Stored reports");
		cmb.setViewName("reports");
		cmb.setAction(Action.GET_ALL);
		navMap.put("getAll", cmb);

		cmb = new ReportManagementBean();
		cmb.setOperationLabel("New Report Creation");
		cmb.setViewName("reportCreation");
		cmb.setAction(Action.CREATE);
		navMap.put("create", cmb);

		cmb = new ReportManagementBean();
		cmb.setOperationLabel("Confirm Report Deletion");
		cmb.setViewName("reportDeletion");
		cmb.setAction(Action.DELETE);
		navMap.put("delete", cmb);

	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());
		ReportManagementBean rmb = navMap.get(section);
		if (rmb != null) {
			req.setAttribute("reportBean", rmb);
			Vega vega = new VegaImpl();
			ReportService rs = vega.getReportService();
			Collection<Report> storedReports = rs.getStoredReports();
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
		ReportManagementBean cmb = navMap.get(section);
		if (cmb != null) {
			Vega vega = new VegaImpl();
			List<Category> categories = vega.getCategoryService().getAllCategories();
			req.setAttribute("categories", categories);
			req.setAttribute("reportBean", cmb);
			req.setAttribute("contextRoot", req.getContextPath());
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
