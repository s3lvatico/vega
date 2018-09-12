package org.gmnz.vega.web.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.web.context.RequestContext;


class CmdCategoryGetAll extends AbstractVegaCommand {

	private Vega vega;



	public CmdCategoryGetAll(RequestContext requestContext) {
		super(requestContext);
		model.setAttribute(VegaCommand.CMD_NAME, VegaCommand.Category.GET_ALL);
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		HttpServletRequest httpRequest = (HttpServletRequest) requestContext.getAttribute(RequestContext.ORIGINAL_REQUEST);
		boolean managementEnabled = httpRequest.isUserInRole("v-admin");
		model.setAttribute("managementEnabled", managementEnabled);
	}



/*
 * * List<Category> categories = categoryService.getAllCategories();
 * req.setAttribute("categories", categories);
 * req.setAttribute("managementEnabled", req.isUserInRole("v-admin"));
 * 
 * (non-Javadoc)
 * 
 * @see org.gmnz.vega.web.command.AbstractVegaCommand#process()
 */
	@Override
	protected void process() {
		try {
			List<Category> categories = vega.getCategoryService().getAllCategories();
			model.setAttribute("categories", categories);
		} catch (VegaException e) {
			e.printStackTrace();
		}
	}

}
