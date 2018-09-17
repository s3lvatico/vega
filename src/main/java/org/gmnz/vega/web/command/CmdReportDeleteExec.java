package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdReportDeleteExec extends AbstractVegaCommand {

	Vega vega;

	String reportId;



	public CmdReportDeleteExec(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Report.EXECUTE_DELETE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		reportId = requestContext.getParameter("reportId");
	}



	@Override
	protected void process() throws VegaException {
		vega.getReportService().removeReport(reportId);
	}

}
