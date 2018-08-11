package org.gmnz.vega.ui.web.report;


import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ToxicityRating;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.ui.Action;


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



	private void executeAction(String action, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid action specified");
				return;
			}
		} catch (VegaException ve) {
			String errorMessage = String.format("exception thrown while executing action -- %s :: %s",
					ve.getClass().getName(), ve.getMessage());
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/app/report/getAll");
	}



	private void createAndStoreReport(String subjectName, HttpServletRequest req, HttpServletResponse resp)
			throws VegaException {
		String reportOwner = req.getRemoteUser();
		Report r = new Report(subjectName, new Date(), reportOwner);
		AllergenService allergenService = vega.getAllergenService();
		Enumeration<String> paramNames = req.getParameterNames();
		String paramName;
		String allergenId;
		while (paramNames.hasMoreElements()) {
			paramName = paramNames.nextElement();
			if (paramName.startsWith("tr-")) {
				allergenId = paramName.substring(3);
				Allergen a = allergenService.getAllergenById(allergenId);
				if (a == null) {
					// TODO questo va sistemato in altro modo
					continue;
				}
				String strToxValue = req.getParameter(paramName);
				double toxValue = Double.parseDouble(strToxValue);
				ToxicityRating tr = new ToxicityRating(a, toxValue);
				r.addRating(tr);
			}
		}
		vega.getReportService().createReport(r);
	}
}
