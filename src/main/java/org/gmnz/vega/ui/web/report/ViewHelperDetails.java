package org.gmnz.vega.ui.web.report;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ToxicityRating;
import org.gmnz.vega.ui.web.report.view.ViewReportCategory;
import org.gmnz.vega.ui.web.report.view.ViewReportData;
import org.gmnz.vega.ui.web.report.view.ViewReportToxicityAssessment;


class ViewHelperDetails extends ViewHelperBase {

	@Override
	protected RequestProcessingOutcome processRequest(HttpServletRequest req, ReportManagementBean rmb) {
		RequestProcessingOutcome outcome = new RequestProcessingOutcome();
		String reportId = req.getParameter("reportId");
		if (VegaUtil.stringNullOrEmpty(reportId)) {
			outcome.statusCode = HttpServletResponse.SC_BAD_REQUEST;
			outcome.errorMessage = "no report id specified";
			return outcome;
		}

		try {
			Report r = vega.getReportService().getReportById(reportId);

			ViewReportData viewReportData = prepareReportData(r);
			req.setAttribute("reportData", viewReportData);
		} catch (VegaException e) {
			e.printStackTrace();
			outcome.statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			outcome.errorMessage = "error while retrieving report";
			return outcome;
		}

		outcome.statusCode = 200;
		return outcome;
	}



	private ViewReportData prepareReportData(Report r) {
		ViewReportData reportData = new ViewReportData();
		reportData.setOwner(r.getOwnerFullName());
		reportData.setSubjectName(r.getSubjectName());
		reportData.setCreationDate(r.getCreationDate());
		for (String categoryName : r.getCategories()) {
			ViewReportCategory vrc = new ViewReportCategory();
			vrc.setName(categoryName);
			for (ToxicityRating rating : r.getRatings(categoryName)) {
				ViewReportToxicityAssessment vta = new ViewReportToxicityAssessment();
				vta.setAllergenName(rating.getAllergen().getName());
				vta.setToxicityRating(rating.getToxicity());
				vrc.getToxData().add(vta);
			}
			reportData.getCategories().add(vrc);
		}
		return reportData;
	}

}
