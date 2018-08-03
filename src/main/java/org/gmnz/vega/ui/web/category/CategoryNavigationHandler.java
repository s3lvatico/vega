package org.gmnz.vega.ui.web.category;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.RequestProcessingResult;


class CategoryNavigationHandler {

	private Map<String, CategoryManagementBean> navigationMap;

	private Vega vega;



	public CategoryNavigationHandler() {
		navigationMap = new HashMap<>();

		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Registered categories");
		cmb.setViewName("categories");
		cmb.setAction(Action.GET_ALL);
		navigationMap.put("getAll", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("New Category Creation");
		cmb.setViewName("categoryManagement");
		cmb.setAction(Action.CREATE);
		navigationMap.put("create", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Modify Category Name");
		cmb.setViewName("categoryManagement");
		cmb.setAction(Action.MODIFY);
		navigationMap.put("edit", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Confirm Category Deletion");
		cmb.setViewName("categoryDeletion");
		cmb.setAction(Action.DELETE);
		navigationMap.put("delete", cmb);

		vega = new VegaImpl();
	}



	RequestProcessingResult handleRequest(String section, HttpServletRequest req, HttpServletResponse resp) {
		CategoryManagementBean mgmtBean = navigationMap.get(section);
		if (mgmtBean != null) {
			return handleAction(mgmtBean, req, resp);
		} else {
			return new RequestProcessingResult(HttpServletResponse.SC_NOT_FOUND, "unknown section requested");
		}
	}



	private RequestProcessingResult handleAction(CategoryManagementBean mgmtBean, HttpServletRequest req,
			HttpServletResponse resp) {
		CategoryService categoryService = vega.getCategoryService();
		switch (mgmtBean.getAction()) {
		case Action.GET_ALL:
			try {
				List<Category> categories = categoryService.getAllCategories();
				req.setAttribute("categories", categories);
				return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
			} catch (VegaException e) {
				e.printStackTrace();
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"error while retrieving categories");
			}
		case Action.MODIFY:
		case Action.DELETE:
			String categoryId = req.getParameter("categoryId");
			try {
				Category c = categoryService.getCategoryById(categoryId);
				mgmtBean.setCategory(c);
			} catch (VegaException e) {
				e.printStackTrace();
				String errorMessage = String.format("error while retrieving category with id [%s]", categoryId);
				return new RequestProcessingResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
			}
			req.setAttribute("catBean", mgmtBean);
			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
		case Action.CREATE:
			mgmtBean.setCategory(new Category(""));
			req.setAttribute("catBean", mgmtBean);
			return new RequestProcessingResult(HttpServletResponse.SC_OK, mgmtBean.getViewName(), null);
		default:
			return new RequestProcessingResult(HttpServletResponse.SC_BAD_REQUEST, "unrecognized request");
		}
	}
}
