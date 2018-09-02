package org.gmnz.vega.ui.web.report;


import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.RequestProcessingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


class ReportRequestHandler {

	private Map<String, ReportManagementBean> navigationMap;



	ReportRequestHandler() {
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

	}



	RequestProcessingResult handleRequest(String section, HttpServletRequest req, HttpServletResponse resp) {
		ReportManagementBean mgmtBean = navigationMap.get(section);
		if (mgmtBean != null) {
			return handleAction(mgmtBean, req, resp);
		} else {
			return RequestProcessingResult.NOT_FOUND("unknown section requested");
		}
	}



	private RequestProcessingResult handleAction(ReportManagementBean mgmtBean, HttpServletRequest req,
																HttpServletResponse resp) {
		RequestProcessingResult result;
		switch (mgmtBean.getAction()) {
			case Action.GET_ALL:
				result = new ViewHelperGetAll().processRequest(req, mgmtBean);
				break;
			case Action.CREATE:
				result = new ViewHelperCreation().processRequest(req, mgmtBean);
				break;
			case Action.VIEW_DETAILS:
				result = new ViewHelperDetails().processRequest(req, mgmtBean);
				break;
			case Action.DELETE:
				result = new ViewHelperDeletion().processRequest(req, mgmtBean);
				break;
			default:
				return RequestProcessingResult.BAD_REQUEST("unrecognized request");
		}
		if (result.getStatusCode() == HttpServletResponse.SC_OK) {
			req.setAttribute("reportBean", mgmtBean);
			return RequestProcessingResult.OK(mgmtBean.getViewName());
		} else {
			return new RequestProcessingResult(result.getStatusCode(), result.getErrorMessage());
		}
	}

}
