package org.gmnz.vega.ui.web.report;


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
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ToxicityRating;
import org.gmnz.vega.service.ReportService;
import org.gmnz.vega.ui.Action;


public class ReportController extends HttpServlet {

	private static final long serialVersionUID = -8297293947108342649L;

	private Map<String, ReportManagementBean> viewMap;



	@Override
	public void init() {
		viewMap = new HashMap<>();

		ReportManagementBean rmb = new ReportManagementBean();
		rmb.setOperationLabel("Stored reports");
		rmb.setViewName("reports");
		rmb.setAction(Action.GET_ALL);
		viewMap.put("getAll", rmb);

		rmb = new ReportManagementBean();
		rmb.setOperationLabel("New Report Creation");
		rmb.setViewName("reportCreation");
		rmb.setAction(Action.CREATE);
		viewMap.put("create", rmb);

		rmb = new ReportManagementBean();
		rmb.setOperationLabel("Confirm Report Deletion");
		rmb.setViewName("reportDeletion");
		rmb.setAction(Action.DELETE);
		viewMap.put("delete", rmb);

		rmb = new ReportManagementBean();
		rmb.setOperationLabel("View Report Details");
		rmb.setViewName("reportView");
		rmb.setAction(Action.VIEW_DETAILS);
		viewMap.put("viewDetails", rmb);

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


	// TODO modificare la logica di navigazione, NON E' AFFATTO CHIARA

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String section = findRequestedSection(req.getRequestURL().toString());
		ReportManagementBean cmb = viewMap.get(section);
		if (cmb != null) {
			Vega vega = new VegaImpl();

			// recupero categorie
			try {
				List<Category> categories = vega.getCategoryService().getAllCategories();
				req.setAttribute("categories", categories);
			} catch (VegaException e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"exception thrown while retrieving the categories");
				return;
			}

			req.setAttribute("reportBean", cmb);
			req.setAttribute("contextRoot", req.getContextPath());

			if (cmb.getAction().equals(Action.VIEW_DETAILS)) {
				String reportId = req.getParameter("reportId");
				Report r = vega.getReportService().getReport(reportId);
				ViewReportData viewReportData = prepareReportData(r);
				req.setAttribute("reportData", viewReportData);
			}
			if (cmb.getAction().equals(Action.DELETE)) {
				String reportId = req.getParameter("reportId");
				Report r = vega.getReportService().getReport(reportId);
				req.setAttribute("subjectName", r.getSubjectName());
				req.setAttribute("creationDate", r.getCreationDate());
				req.setAttribute("reportId", reportId);
			}

			String targetUrl = String.format("/WEB-INF/jsp/%s.jsp", cmb.getViewName());
			req.getRequestDispatcher(targetUrl).forward(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "unknown section: <" + section + ">");
		}
	}



	private ViewReportData prepareReportData(Report r) {
		ViewReportData reportData = new ViewReportData();
		reportData.setSubjectName(r.getSubjectName());
		reportData.setCreationDate(r.getCreationDate());
		for (String categoryName : r.getCategories()) {
			ViewReportCategory vrc = new ViewReportCategory();
			vrc.setName(categoryName);
			for (ToxicityRating rating : r.getRatings(categoryName)) {
				ViewToxicityAssessment vta = new ViewToxicityAssessment();
				vta.setAllergenName(rating.getAllergen().getName());
				vta.setToxicityRating(rating.getToxicity());
				vrc.getToxData().add(vta);
			}
			reportData.getCategories().add(vrc);
		}
		return reportData;
	}



	private String findRequestedSection(String requestUrl) {
		int i = requestUrl.lastIndexOf('/');
		// forse c'è da gestire il caso in cui ritorna -1
		return requestUrl.substring(i + 1);
	}
}
