package org.gmnz.vega.ui.web.allergen;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.RequestProcessingResult;


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
		String paramAllergenId = req.getParameter("allergenId");
		switch (mgmtBean.getAction()) {
		case Action.GET_ALL:
			try {
				List<Allergen> allergens = allergenService.getAllAllergens();
				req.setAttribute("allergens", allergens);
				req.setAttribute("managementEnabled", req.isUserInRole("v-admin"));
				return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
			} catch (VegaException e) {
				e.printStackTrace();
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"error while retrieving allergens");
			}
		case Action.CREATE:
			// recupero le categorie
			RequestProcessingResult injectCategoriesError = injectCategories(req);
			if (injectCategoriesError != null) {
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"error while retrieving categories");
			}
			mgmtBean.setAllergen(new Allergen(""));
			req.setAttribute("allergenBean", mgmtBean);
			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
		case Action.MODIFY:
			if (injectAllergen(mgmtBean, paramAllergenId) != null) {
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"error while retrieving allergen from id " + paramAllergenId);
			}
			// salva in sessione l'allergene iniziale
			req.getSession().setAttribute("allergen", mgmtBean.getAllergen());

			// recupero categorie
			if (injectCategories(req) != null) {
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"error while retrieving categories");
			}
			// categoria iniziale dell'allergene
			req.setAttribute("initialAllergenCategoryName", mgmtBean.getAllergen().getCategory().getName());
			req.setAttribute("allergenBean", mgmtBean);
			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
		case Action.DELETE:
			if (VegaUtil.stringNullOrEmpty(paramAllergenId)) {
				return new RequestProcessingResult(HttpServletResponse.SC_BAD_REQUEST, "no allergen id specified");
			}
			RequestProcessingResult rpr = injectAllergen(mgmtBean, paramAllergenId);
			if (rpr != null) {
				return rpr;
			}
			req.setAttribute("allergenBean", mgmtBean);
			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
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
		} catch (VegaException e) {
			e.printStackTrace();
			return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"error while retrieving allergen");
		}
	}
}
