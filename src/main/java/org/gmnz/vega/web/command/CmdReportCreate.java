package org.gmnz.vega.web.command;


import java.util.List;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.report.ReportManagementBean;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdReportCreate extends AbstractVegaCommand {

	private Vega vega;



	public CmdReportCreate(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Report.CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
	}



	@Override
	protected void process() throws Exception {
		ReportManagementBean rmb = new ReportManagementBean();
		rmb.setOperationLabel("Creation of a new report");
		rmb.setAction(VegaCommand.Report.CREATE);
		model.setAttribute("reportBean", rmb);

		List<Category> categories = vega.getCategoryService().getAllCategoriesWithAllergens();
		model.setAttribute("categories", categories);
	}

}
