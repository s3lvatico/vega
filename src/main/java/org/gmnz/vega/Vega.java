package org.gmnz.vega;


import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.service.ReportService;
import org.gmnz.vega.service.UserService;


public interface Vega {

	void assignAllergenToCategory(String allergenName, String categoryName);


	CategoryService getCategoryService();


	AllergenService getAllergenService();


	ReportService getReportService();


	UserService getUserService();

}
