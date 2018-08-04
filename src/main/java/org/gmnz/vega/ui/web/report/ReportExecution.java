package org.gmnz.vega.ui.web.report;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.base.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.ui.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;


/**
 * creato da simone in data 12/07/2018.
 */
public class ReportExecution extends HttpServlet {

	private static final long serialVersionUID = -9021626655309704727L;

	private Vega vega;



	@Override
	public void init() {
		vega = new VegaImpl();
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null || action.isEmpty()) {
			throw new ServletException("no action specified");
		}
		executeAction(action, req, resp);
	}



	private void executeAction(String action, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			switch (action) {
				case Action.CREATE:
					String subjectName = req.getParameter("subjectName");
					if (VegaUtil.stringNullOrEmpty(subjectName)) {
						resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "A subject must be specified");
						return;
					}
					createAndStoreReport(subjectName, req, resp);
					break;
				case Action.DELETE:
					String reportId = req.getParameter("reportId");
					if (!VegaUtil.stringNullOrEmpty(reportId)) {
						vega.getReportService().removeReport(reportId);
					}
					break;
				default:
					throw new ServletException("invalid action specified");
			}
		} catch (VegaException ve) {
			String errorMessage = String.format("exception thrown while executing action -- %s :: %s",
					ve.getClass().getName(), ve.getMessage());
			throw new ServletException(errorMessage, ve);
		}
		resp.sendRedirect(req.getContextPath() + "/report/getAll");
	}



	private void createAndStoreReport(String subjectName, HttpServletRequest req, HttpServletResponse resp)
			throws VegaException {
		// TODO ReportExecution.createAndStoreReport - fare
		Report r = new Report(subjectName, new Date());
		AllergenService allergenService = vega.getAllergenService();
		Enumeration<String> paramNames = req.getParameterNames();
		String paramName;
		String allergenName;
		while (paramNames.hasMoreElements()) {
			paramName = paramNames.nextElement();
			if (paramName.startsWith("Tox")) {
				allergenName = paramName.substring(3);
				String strToxValue = req.getParameter(paramName);
				double toxValue = Double.parseDouble(strToxValue);
//				Allergen a = allergenService.get(allergenName);
//				ToxicityRating tr = new ToxicityRating(null, toxValue);
//				r.addRating(tr);
			}
		}
		vega.getReportService().addReport(r);
	}
}
