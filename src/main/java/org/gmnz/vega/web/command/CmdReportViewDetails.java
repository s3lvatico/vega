package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ToxicityRating;
import org.gmnz.vega.ui.web.report.ReportManagementBean;
import org.gmnz.vega.ui.web.report.view.ViewReportCategory;
import org.gmnz.vega.ui.web.report.view.ViewReportData;
import org.gmnz.vega.ui.web.report.view.ViewReportToxicityAssessment;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdReportViewDetails extends AbstractVegaCommand {

	Vega vega;
	String reportId;



	public CmdReportViewDetails(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Report.VIEW_DETAILS;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		reportId = requestContext.getParameter("reportId");
	}



	@Override
	protected void process() throws Exception {
		ReportManagementBean rmb = new ReportManagementBean();
		rmb.setOperationLabel("Report details");
		Report r = vega.getReportService().getReportById(reportId);
		ViewReportData viewReportData = prepareReportData(r);
		model.setAttribute("reportData", viewReportData);
	}



	private ViewReportData prepareReportData(Report r) {
		ViewReportData reportData = new ViewReportData();
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
