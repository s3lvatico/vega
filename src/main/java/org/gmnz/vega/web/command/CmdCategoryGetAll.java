package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.web.context.ContextProperty;
import org.gmnz.vega.web.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


class CmdCategoryGetAll extends AbstractVegaCommand {

	private Vega vega;




	public CmdCategoryGetAll(RequestContext requestContext) {

		super(requestContext);
		model.setAttribute(VegaCommand.CMD_NAME, VegaCommand.Category.GET_ALL);

	}




	@Override
	protected void initialize(RequestContext requestContext) {

		vega = new VegaImpl();
		HttpServletRequest httpRequest = (HttpServletRequest) requestContext.getAttribute(ContextProperty.ORIGINAL_REQUEST);
		boolean managementEnabled = httpRequest.isUserInRole("v-admin");
		model.setAttribute("managementEnabled", managementEnabled);

	}




	@Override
	protected void process() {

		try {
			List<Category> categories = vega.getCategoryService().getAllCategories();
			model.setAttribute("categories", categories);
		}
		catch (VegaException e) {
			e.printStackTrace();
			String errorMessage = String.format("%s command error : %s", getCommandName(), e.getMessage());
			markForError("APPLICATION_ERROR", 500, errorMessage, e);
		}

	}

}
