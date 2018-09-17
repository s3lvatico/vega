package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.ui.web.report.ReportManagementBean;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdReportDelete extends AbstractVegaCommand {

	Vega vega;
	String reportId;



	public CmdReportDelete(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Report.DELETE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		reportId = requestContext.getParameter("reportId");
	}



	@Override
	protected void process() throws Exception {
		ReportManagementBean rmb = new ReportManagementBean();
		rmb.setOperationLabel("Confirm Report Deletion");
		rmb.setViewName("reportDeletion");
		rmb.setAction(setCommandName());

		model.setAttribute("reportBean", rmb);

		Report r = vega.getReportService().getReportSummaryById(reportId);
		if (r != null) {
			model.setAttribute("subjectName", r.getSubjectName());
			model.setAttribute("creationDate", r.getCreationDate());
			model.setAttribute("reportId", reportId);
		} else {
			throw new Exception(String.format("No report found with id [%s]", reportId));
		}

	}

}
