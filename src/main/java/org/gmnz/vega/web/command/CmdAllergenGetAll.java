package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


class CmdAllergenGetAll extends AbstractVegaCommand {

	private Vega vega;



	public CmdAllergenGetAll(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Allergen.GET_ALL;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		HttpServletRequest httpRequest = (HttpServletRequest) requestContext.getAttribute(ContextProperty.ORIGINAL_REQUEST);
		boolean managementEnabled = httpRequest.isUserInRole("v-admin");
		model.setAttribute("managementEnabled", managementEnabled);
	}



	@Override
	protected void process() throws Exception {
		List<Allergen> allergens = vega.getAllergenService().getAllAllergens();
		model.setAttribute("allergens", allergens);
	}

}
