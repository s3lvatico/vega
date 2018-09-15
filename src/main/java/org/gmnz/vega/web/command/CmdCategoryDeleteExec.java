package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdCategoryDeleteExec extends AbstractVegaCommand {

	private Vega vega;

	private String categoryId;



	public CmdCategoryDeleteExec(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Category.EXECUTE_DELETE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		categoryId = requestContext.getParameter("categoryId");
	}



	@Override
	protected void process() throws VegaException {
		vega.getCategoryService().removeCategory(categoryId);
	}

}
