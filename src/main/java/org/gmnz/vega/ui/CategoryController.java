package org.gmnz.vega.ui;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CategoryController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.format( "request URL: <%s>%n", req.getRequestURL() );
		System.out.format( "request URI: <%s>%n", req.getRequestURI() );
		String section = extractRequestedSection(req.getRequestURL().toString());
		System.out.println( "section requested: " + section );
		req.getRequestDispatcher("/categories.jsp").forward(req, resp);
	}

	private String extractRequestedSection(String requestUrl) {
		int i = requestUrl.lastIndexOf('/');
		// forse c'è da gestire il caso in cui ritorna -1
		return requestUrl.substring(i+1);
	}
}
