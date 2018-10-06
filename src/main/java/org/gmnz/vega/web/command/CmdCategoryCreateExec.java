package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdCategoryCreateExec extends AbstractVegaCommand {

	private Vega vega;

	private String newCategoryName;



	public CmdCategoryCreateExec(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Category.EXECUTE_CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		newCategoryName = requestContext.getParameter("categoryName");
	}



	@Override
	protected void process() throws VegaException {
		vega.getCategoryService().createCategory(newCategoryName);
	}

}
