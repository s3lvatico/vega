package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.allergen.AllergenManagementBean;
import org.gmnz.vega.web.context.RequestContext;

import java.util.List;


/**
 * creato da simone in data 15/09/2018.
 */
class CmdAllergenCreate extends AbstractVegaCommand {

	private Vega vega;



	public CmdAllergenCreate(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Allergen.CREATE;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
	}



	@Override
	protected void process() throws VegaException {
		AllergenManagementBean cmb = new AllergenManagementBean();
		cmb.setOperationLabel("New allergen creation");
		cmb.setAction(VegaCommand.Allergen.CREATE);
		model.setAttribute("allergenBean", cmb);

		List<Category> categories = vega.getCategoryService().getAllCategories();
		model.setAttribute("categories", categories);

	}

}
