package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.category.CategoryManagementBean;
import org.gmnz.vega.web.context.RequestContext;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdCategoryDelete extends AbstractVegaCommand {

	private Vega vega;
	private String categoryId;



	public CmdCategoryDelete(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Category.DELETE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		categoryId = requestContext.getParameter("categoryId");
	}



	@Override
	protected void process() throws Exception {
		Category category =  vega.getCategoryService().getCategoryById(categoryId);
		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Category deletion");
		cmb.setCategory(category);
		cmb.setAction(getCommandName());
		model.setAttribute("categoryBean", cmb);
	}

}
