package org.gmnz.vega.web.command;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.ui.web.RequestProcessingResult;
import org.gmnz.vega.ui.web.allergen.AllergenManagementBean;
import org.gmnz.vega.ui.web.category.CategoryManagementBean;
import org.gmnz.vega.web.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * creato da simone in data 16/09/2018.
 */
// TODO completare
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

		Allergen targetAllergen = vega.getAllergenService().getAllergenById(allergenId);
		if (targetAllergen == null) {
			throw new Exception("no allergen found with id [" + allergenId +"]");
		}
		amb.setAllergen(targetAllergen);

		// salva in sessione l'allergene iniziale
		model.set req.getSession().setAttribute("allergen", mgmtBean.getAllergen());

		// recupero categorie
		if (injectCategories(req) != null) {
			return RequestProcessingResult.INTERNAL_SERVER_ERROR("error while retrieving categories");
		}
		// categoria iniziale dell'allergene
		req.setAttribute("initialAllergenCategoryName", mgmtBean.getAllergen().getCategory().getName());
		req.setAttribute("allergenBean", mgmtBean);




		Category category = vega.getCategoryService().getCategoryById(categoryId);
		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Change category name");
		cmb.setCategory(category);
		cmb.setAction(getCommandName());
		model.setAttribute("categoryBean", cmb);
	}


	private RequestProcessingResult injectCategories(HttpServletRequest request) {
		try {
			List<Category> categories = vega.getCategoryService().getAllCategories();
			request.setAttribute("categories", categories);
		}
		catch (VegaException e) {
			e.printStackTrace();
			return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"error while retrieving categories");
		}
		return null;
	}



	private RequestProcessingResult injectAllergen(AllergenManagementBean mgmtBean, String allergenId) {
		try {
			// recupero allergene
			Allergen targetAllergen = vega.getAllergenService().getAllergenById(allergenId);
			if (targetAllergen == null) {
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"no allergen found with id " + allergenId);
			}
			mgmtBean.setAllergen(targetAllergen);
			return null;
		}
		catch (VegaException e) {
			e.printStackTrace();
			return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"error while retrieving allergen");
		}
	}
}
