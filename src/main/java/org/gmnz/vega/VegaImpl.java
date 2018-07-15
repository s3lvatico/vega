package org.gmnz.vega;


import org.gmnz.vega.service.*;


public class VegaImpl implements Vega {

	private final CategoryService categoryService;

	private final AllergenService allergenService;

	private final ReportService reportService;



	public VegaImpl() {
		categoryService = new CategoryServiceImpl();
		allergenService = new AllergenServiceImpl();
		reportService = new ReportServiceImpl();
	}



	@Override
	public CategoryService getCategoryService() {
		return categoryService;
	}



	@Override
	public AllergenService getAllergenService() {
		return allergenService;
	}



	@Override
	public ReportService getReportService() {
		return reportService;
	}



	@Override
	public void assignAllergenToCategory(String nomeAllergene, String nomeCategoria) {
		throw new RuntimeException("not yet implemented");
	}

}
