package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 16/09/2018.
 */
// TODO completare
public class CmdAllergenEditExecute extends AbstractVegaCommand {

	String categoryName;
	String categoryId;

	Vega vega;



	public CmdAllergenEditExecute(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Category.EXECUTE_EDIT;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		categoryId = requestContext.getParameter("categoryId");
		categoryName = requestContext.getParameter("categoryName");
	}



	@Override
	protected void process() throws Exception {
		vega.getCategoryService().changeCategoryName(categoryId, categoryName);
	}

}
