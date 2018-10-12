package org.gmnz.vega.web;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaException;
import org.gmnz.vega.VegaImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * creato da simone in data 12/10/2018.
 */
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = -2446914894675049717L;



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Vega v = new VegaImpl();
		try {
			int nCategories = v.getCategoryService().getAllCategories().size();
			req.setAttribute("nCategories", nCategories);
			int nAllergens = v.getAllergenService().getAllAllergens().size();
			req.setAttribute("nAllergens", nAllergens);
			int nUsers = v.getUserService().countUsers();
			req.setAttribute("nUsers", nUsers);
			int nReports = v.getReportService().countReports();
			req.setAttribute("nReports", nReports);
		}
		catch (VegaException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
	}

}
