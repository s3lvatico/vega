package org.gmnz.vega.ui.web.category;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaFactory;
import org.gmnz.vega.VegaImpl;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.ui.Action;
import org.gmnz.vega.ui.web.RequestProcessingResult;


class CategoryRequestHandler {

	private Map<String, CategoryManagementBean> navigationMap;

	CategoryRequestHandler() {
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
		Vega vega = VegaFactory.getFactory().buildVega();
		CategoryService categoryService = vega.getCategoryService();
		switch (mgmtBean.getAction()) {
		case Action.GET_ALL:
			try {
				List<Category> categories = categoryService.getAllCategories();
				req.setAttribute("categories", categories);
				req.setAttribute("managementEnabled", req.isUserInRole("v-admin"));
				return RequestProcessingResult.OK(mgmtBean.getViewName());
			} catch (VegaException e) {
				e.printStackTrace();
				return RequestProcessingResult.INTERNAL_SERVER_ERROR("error while retrieving categories");
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
				return RequestProcessingResult.INTERNAL_SERVER_ERROR(errorMessage);
			}
			req.setAttribute("catBean", mgmtBean);
			return RequestProcessingResult.OK(mgmtBean.getViewName());
		case Action.CREATE:
			mgmtBean.setCategory(new Category(""));
			req.setAttribute("catBean", mgmtBean);
			return RequestProcessingResult.OK(mgmtBean.getViewName());
		default:
			return RequestProcessingResult.BAD_REQUEST("unrecognized request");
		}
	}
}
