package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.ui.web.RequestProcessingResult;
import org.gmnz.vega.ui.web.allergen.AllergenManagementBean;
import org.gmnz.vega.web.context.RequestContext;

import javax.servlet.http.HttpServletResponse;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdAllergenDelete extends AbstractVegaCommand {

	private Vega vega;
	private String allergenId;



	public CmdAllergenDelete(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Allergen.DELETE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		allergenId = requestContext.getParameter("allergenId");
	}



	@Override
	protected void process() throws Exception {
		Allergen targetAllergen = vega.getAllergenService().getAllergenById(allergenId);
		if (targetAllergen == null) {
			throw new Exception(String.format("No allergen found with id [%s]", allergenId));
		}
		AllergenManagementBean amb = new AllergenManagementBean();
		amb.setAction(VegaCommand.Allergen.DELETE);
		amb.setOperationLabel("Allergen deletion");
		amb.setAllergen(targetAllergen);
		model.setAttribute("allergenBean", amb);

	}

}
