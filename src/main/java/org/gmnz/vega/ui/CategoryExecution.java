package org.gmnz.vega.ui;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CategoryExecution extends HttpServlet {

	private static final long serialVersionUID = -4187200629703344994L;



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null || action.isEmpty()) {
			throw new ServletException("invalid action requested");
		}
		System.out.format("action requested: <%s>", action);
		req.getRequestDispatcher("/categories.jsp").forward(req, resp);
	}



	private void executeCommand(String command, HttpServletRequest req, HttpServletResponse resp) {
		// TODO gestisci il comando
	}
}
