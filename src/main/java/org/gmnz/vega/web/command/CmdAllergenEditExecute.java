package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 16/09/2018.
 */
// TODO completare
public class CmdAllergenEditExecute extends AbstractVegaCommand {

	String allergenName;
	String categoryId;

	Allergen originalAllergen;
	Vega vega;



	public CmdAllergenEditExecute(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Allergen.EXECUTE_EDIT;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		categoryId = requestContext.getParameter("categoryId");
		allergenName = requestContext.getParameter("allergenName");
		originalAllergen = (Allergen) requestContext.getSessionAttribute("allergen");
	}



	@Override
	protected void process() throws Exception {
		if (originalAllergen == null) {
			throw new Exception("no original allergen stored in session");
		}
		if (VegaUtil.stringNullOrEmpty(allergenName) || VegaUtil.stringNullOrEmpty(categoryId)) {
			throw new Exception("empty values for parameters are not allowed");
		}
		vega.getAllergenService().modifyAllergen(originalAllergen, allergenName, categoryId);
	}

}
