package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


class CmdUserGetAll extends AbstractVegaCommand {

	private Vega vega;



	public CmdUserGetAll(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.User.GET_ALL;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
	}



	@Override
	protected void process() throws Exception {
		Collection<Report> reports = vega.getReportService().getStoredReports();
		model.setAttribute("users", reports);
	}

}
