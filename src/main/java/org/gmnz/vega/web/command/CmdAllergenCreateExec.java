package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdAllergenCreateExec extends AbstractVegaCommand {

	private Vega vega;

	String allergenName;
	String categoryId;



	public CmdAllergenCreateExec(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Allergen.EXECUTE_CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		allergenName = requestContext.getParameter("allergenName");
		categoryId = requestContext.getParameter("categoryId");

	}



	@Override
	protected void process() throws VegaException {
		vega.getAllergenService().createAllergen(allergenName, categoryId);
	}

}
