package org.gmnz.vega.web.command;


import org.gmnz.vega.ui.web.category.CategoryManagementBean;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
public class CmdCategoryCreate extends AbstractVegaCommand {

	public CmdCategoryCreate(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Category.CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {

	}



	@Override
	protected void process() {
		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("New category creation");
		cmb.setAction(VegaCommand.Category.CREATE);
		model.setAttribute("categoryBean", cmb);
	}

}
