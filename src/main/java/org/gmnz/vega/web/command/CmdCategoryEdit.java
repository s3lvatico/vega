package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.category.CategoryManagementBean;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 16/09/2018.
 */
class CmdCategoryEdit extends AbstractVegaCommand {

	private Vega vega;
	private String categoryId;



	public CmdCategoryEdit(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Category.EDIT;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		categoryId = requestContext.getParameter("categoryId");
	}



	@Override
	protected void process() throws Exception {
		Category category = vega.getCategoryService().getCategoryById(categoryId);
		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Change category name");
		cmb.setCategory(category);
		cmb.setAction(getCommandName());
		model.setAttribute("categoryBean", cmb);
	}

}
