package org.gmnz.vega.ui.web.allergen;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.RequestProcessingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


class AllergenNavigationHandler {

	private Map<String, AllergenManagementBean> navigationMap;

	private Vega vega;



	public AllergenNavigationHandler() {
		navigationMap = new HashMap<>();

		AllergenManagementBean mgmtBean = new AllergenManagementBean();
		mgmtBean.setOperationLabel("Registered allergens");
		mgmtBean.setViewName("allergens");
		mgmtBean.setAction(Action.GET_ALL);
		navigationMap.put("getAll", mgmtBean);

		mgmtBean = new AllergenManagementBean();
		mgmtBean.setOperationLabel("New Allergen Creation");
		mgmtBean.setViewName("allergenManagement");
		mgmtBean.setAction(Action.CREATE);
		navigationMap.put("create", mgmtBean);

		mgmtBean = new AllergenManagementBean();
		mgmtBean.setOperationLabel("Modify Allergen");
		mgmtBean.setViewName("allergenManagement");
		mgmtBean.setAction(Action.MODIFY);
		navigationMap.put("edit", mgmtBean);

		mgmtBean = new AllergenManagementBean();
		mgmtBean.setOperationLabel("Confirm Allergen Deletion");
		mgmtBean.setViewName("allergenDeletion");
		mgmtBean.setAction(Action.DELETE);
		navigationMap.put("delete", mgmtBean);

		vega = new VegaImpl();
	}



	RequestProcessingResult handleRequest(String section, HttpServletRequest req, HttpServletResponse resp) {
		AllergenManagementBean mgmtBean = navigationMap.get(section);
		if (mgmtBean != null) {
			return handleAction(mgmtBean, req, resp);
		} else {
			return new RequestProcessingResult(HttpServletResponse.SC_NOT_FOUND, "unknown section requested");
		}
	}



	private RequestProcessingResult handleAction(AllergenManagementBean mgmtBean, HttpServletRequest req,
																HttpServletResponse resp) {
		AllergenService allergenService = vega.getAllergenService();
		switch (mgmtBean.getAction()) {
			case Action.GET_ALL:
				try {
					List<Allergen> allergens = allergenService.getAllAllergens();
					req.setAttribute("allergens", allergens);
					return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
				} catch (VegaException e) {
					e.printStackTrace();
					return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							"error while retrieving allergens");
				}
			case Action.CREATE:
				// recupero le categorie
				if (injectCategories(req) != null) {
					return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error while retrieving categories");
				}
				mgmtBean.setAllergen(new Allergen(""));
				req.setAttribute("allergenBean", mgmtBean);
				return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
			case Action.MODIFY:
				String allergenId = req.getParameter("allergenId");
				Allergen targetAllergen = null;
				try {
					// recupero allergene
					targetAllergen = allergenService.getAllergenById(allergenId);
					if (targetAllergen == null) {
						return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
								"no allergen found with id " + allergenId);
					}
					mgmtBean.setAllergen(targetAllergen);

				} catch (VegaException e) {
					e.printStackTrace();
					return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							"error while retrieving allergens");
				}
				req.setAttribute("trackingId", UUID.randomUUID().toString());

				// recupero categorie
				if (injectCategories(req) != null) {
					return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error while retrieving categories");
				}
				// categoria iniziale dell'allergene
				req.setAttribute("initialAllergenCategoryName", targetAllergen.getCategory().getName());
				req.setAttribute("allergenBean", mgmtBean);
				return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
			case Action.DELETE:
				// TODO : Allergen : DELETE

//			try {
				// Allergen a = allergenService.getAllergenById(allergenId);
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "not yet implemented");
//			} catch (VegaException e) {
//				e.printStackTrace();
//				String errorMessage = String.format("error while managing allergen with id [%s]", allergenId);
//				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
//			}
//			req.setAttribute("catBean", mgmtBean);
//			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
			default:
				return new RequestProcessingResult(HttpServletResponse.SC_BAD_REQUEST, "unrecognized request");
		}
	}



	private RequestProcessingResult injectCategories(HttpServletRequest request) {
		try {
			List<Category> categories = vega.getCategoryService().getAllCategories();
			request.setAttribute("categories", categories);
		} catch (VegaException e) {
			e.printStackTrace();
			return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error while retrieving categories");
		}
		return null;

	}
}
