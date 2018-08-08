package org.gmnz.vega.ui.web.report;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.service.ReportService;
import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.RequestProcessingResult;
import org.gmnz.vega.ui.web.category.CategoryManagementBean;


class ReportNavigationHandler {

	private Map<String, ReportManagementBean> navigationMap;

	private Vega vega;



	ReportNavigationHandler() {
		navigationMap = new HashMap<>();
		ReportManagementBean rmb = new ReportManagementBean();
		rmb.setOperationLabel("Stored reports");
		rmb.setViewName("reports");
		rmb.setAction(Action.GET_ALL);
		navigationMap.put("getAll", rmb);

		rmb = new ReportManagementBean();
		rmb.setOperationLabel("New Report Creation");
		rmb.setViewName("reportCreation");
		rmb.setAction(Action.CREATE);
		navigationMap.put("create", rmb);

		rmb = new ReportManagementBean();
		rmb.setOperationLabel("Confirm Report Deletion");
		rmb.setViewName("reportDeletion");
		rmb.setAction(Action.DELETE);
		navigationMap.put("delete", rmb);

		rmb = new ReportManagementBean();
		rmb.setOperationLabel("View Report Details");
		rmb.setViewName("reportView");
		rmb.setAction(Action.VIEW_DETAILS);
		navigationMap.put("viewDetails", rmb);

		vega = new VegaImpl();
	}
	
	RequestProcessingResult handleRequest(String section, HttpServletRequest req, HttpServletResponse resp) {
		ReportManagementBean mgmtBean = navigationMap.get(section);
		if (mgmtBean != null) {
			return handleAction(mgmtBean, req, resp);
		} else {
			return new RequestProcessingResult(HttpServletResponse.SC_NOT_FOUND, "unknown section requested");
		}
	}
	
	private RequestProcessingResult handleAction(ReportManagementBean mgmtBean, HttpServletRequest req,
			HttpServletResponse resp) {
		ReportService reportService = vega.getReportService();
		switch (mgmtBean.getAction()) {
		case Action.GET_ALL:
			try {
				Collection<Report> storedReports = null;
				try {
					storedReports = reportService.getStoredReports();
					req.setAttribute("reports", storedReports);
				} catch (VegaException e) {
					e.printStackTrace();
					return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							"error while retrieving reports");
				}
				return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
			} catch (VegaException e) {
				e.printStackTrace();
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"error while retrieving categories");
			}
		case Action.DELETE:
			String categoryId = req.getParameter("categoryId");
			try {
				Category c = categoryService.getCategoryById(categoryId);
				mgmtBean.setCategory(c);
			} catch (VegaException e) {
				e.printStackTrace();
				String errorMessage = String.format("error while retrieving category with id [%s]", categoryId);
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			}
			req.setAttribute("catBean", mgmtBean);
			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
		case Action.CREATE:
			mgmtBean.setCategory(new Category(""));
			req.setAttribute("catBean", mgmtBean);
			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
		default:
			return new RequestProcessingResult(HttpServletResponse.SC_BAD_REQUEST, "unrecognized request");
		}
	}
	}

}
