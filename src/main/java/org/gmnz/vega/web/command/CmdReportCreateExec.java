package org.gmnz.vega.web.command;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Report;
import org.gmnz.vega.domain.ReportBuilder;
import org.gmnz.vega.domain.ToxicityRating;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdReportCreateExec extends AbstractVegaCommand {

	private Vega vega;

	private String subjectName;
	private Map<String, String> toxRatings;
	private String reportOwner;



	public CmdReportCreateExec(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Report.EXECUTE_CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		subjectName = requestContext.getParameter("subjectName");
		toxRatings = new HashMap<>();
		for (String paramName : requestContext.getParameterNames()) {
			if (paramName.startsWith("tr-")) {
				toxRatings.put(paramName.substring(3), requestContext.getParameter(paramName));
			}
		}
		HttpServletRequest hsr = (HttpServletRequest) requestContext.getAttribute(ContextProperty.ORIGINAL_REQUEST);
		reportOwner = hsr.getRemoteUser();
	}



	@Override
	protected void process() throws VegaException {

		if (VegaUtil.stringNullOrEmpty(subjectName)) {
			throw new VegaException("the name of the subject is mandatory");
		}

		ReportBuilder builder = ReportBuilder.getBuilder();

		builder.subjectName(subjectName).owner(reportOwner).createdOn(new Date());
		Report r = builder.build();
		AllergenService allergenService = vega.getAllergenService();

		for (Map.Entry<String, String> rating : toxRatings.entrySet()) {
			String allergenId = rating.getKey();
			Allergen a = allergenService.getAllergenById(allergenId);
			if (a == null) {
				System.err.println(
						"WARNING : no allergen found with id <" + allergenId + "> - no toxicity rating stored for this allergen");
				continue;
			}
			double toxValue = Double.parseDouble(rating.getValue());
			ToxicityRating tr = new ToxicityRating(a, toxValue);
			r.addRating(tr);
		}
		vega.getReportService().createReport(r);
	}

}
