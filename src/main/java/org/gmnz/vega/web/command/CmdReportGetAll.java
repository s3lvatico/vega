package org.gmnz.vega.web.command;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;


class CmdReportGetAll extends AbstractVegaCommand {

	private Vega vega;



	public CmdReportGetAll(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Category.GET_ALL;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		HttpServletRequest httpRequest = (HttpServletRequest) requestContext.getAttribute(ContextProperty.ORIGINAL_REQUEST);
		String currentUser = httpRequest.getRemoteUser();
		model.setAttribute("currentUser", currentUser);
	}



	@Override
	protected void process() throws Exception {
		Collection<Report> reports = vega.getReportService().getStoredReports();
		model.setAttribute("reports", reports);
	}

}
