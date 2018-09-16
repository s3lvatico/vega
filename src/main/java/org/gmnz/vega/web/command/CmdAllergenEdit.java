package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.RequestProcessingResult;
import org.gmnz.vega.ui.web.allergen.AllergenManagementBean;
import org.gmnz.vega.web.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * creato da simone in data 16/09/2018.
 */
class CmdAllergenEdit extends AbstractVegaCommand {

	private Vega vega;
	private String allergenId;



	public CmdAllergenEdit(RequestContext requestContext) {
		super(requestContext);
	}



	@Override
	protected String setCommandName() {
		return VegaCommand.Allergen.EDIT;
	}



	@Override
	protected void initialize(RequestContext requestContext) {
		vega = new VegaImpl();
		allergenId = requestContext.getParameter("allergenId");
	}



	@Override
	protected void process() throws Exception {
		AllergenManagementBean amb = new AllergenManagementBean();
		amb.setAction(getCommandName());
		amb.setOperationLabel("Edit allergen");

		Allergen targetAllergen = vega.getAllergenService().getAllergenById(allergenId);
		if (targetAllergen == null) {
			throw new Exception("no allergen found with id [" + allergenId + "]");
		}
		amb.setAllergen(targetAllergen);

		// salva in sessione l'allergene iniziale
		model.storeInSession("allergen", amb.getAllergen());

		// recupero categorie
		List<Category> categories = vega.getCategoryService().getAllCategories();
		model.setAttribute("categories", categories);

		// categoria iniziale dell'allergene
		model.setAttribute("initialAllergenCategoryName", amb.getAllergen().getCategory().getName());

		model.setAttribute("allergenBean", amb);

	}

}
