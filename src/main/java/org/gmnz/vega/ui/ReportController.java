package org.gmnz.vega.ui;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ReportController extends HttpServlet {

	private Map<String, Object> navMap;



	@Override
	public void init() {
		navMap = new HashMap<>();

		CategoryManagementBean cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Registered categories");
		cmb.setViewName("categories");
		cmb.setAction(Action.GET_ALL);
		navMap.put("getAll", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("New Category Creation");
		cmb.setViewName("categoryManagement");
		cmb.setAction(Action.CREATE);
		navMap.put("create", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Modify Category Name");
		cmb.setViewName("categoryManagement");
		cmb.setAction(Action.MODIFY);
		navMap.put("edit", cmb);

		cmb = new CategoryManagementBean();
		cmb.setOperationLabel("Confirm Category Deletion");
		cmb.setViewName("categoryDeletion");
		cmb.setAction(Action.DELETE);
		navMap.put("delete", cmb);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
