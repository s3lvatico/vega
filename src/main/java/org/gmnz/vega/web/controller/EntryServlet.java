package org.gmnz.vega.web.controller;


import org.gmnz.vega.VegaUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EntryServlet extends HttpServlet {


	private static final long serialVersionUID = 6464966024949529331L;



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath();
		req.setAttribute("contextRoot", contextPath);

		boolean userManagementEnabled = req.isUserInRole("v-admin");
		req.getSession().setAttribute("userManagementEnabled", userManagementEnabled);

		boolean userIsLogged = !VegaUtil.stringNullOrEmpty(req.getRemoteUser());
		req.setAttribute("userIsLogged", userIsLogged);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/mainMenu.jsp");
		dispatcher.forward(req, resp);
	}

}
