package org.gmnz.vega.ui.web;


import org.gmnz.vega.Vega;
import org.gmnz.vega.VegaFactory;
import org.gmnz.vega.VegaUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * creato da simone in data 01/09/2018.
 */
public abstract class ExecutionServlet extends HttpServlet {

	private static final long serialVersionUID = -694122046947657885L;

	protected Vega vega;



	@Override
	public void init() {
		vega = VegaFactory.getFactory().buildVega();
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (VegaUtil.stringNullOrEmpty(action)) {
			String errorMessage = "no action specified";
			req.setAttribute("errorMessage", errorMessage);
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMessage);
			return;
		}
		executeAction(action, req, resp);
	}



	protected abstract void executeAction(String action, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;


}
