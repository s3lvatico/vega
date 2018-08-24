package org.gmnz.vega;


import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.service.ReportService;
import org.gmnz.vega.service.ReportServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class VegaImpl implements Vega, ApplicationContextAware {

	// private final CategoryService categoryService;

	// private final AllergenService allergenService;

	// private final ReportService reportService;

	private ApplicationContext applicationContext;

	public VegaImpl() {
//		categoryService = new CategoryServiceImpl();
//		allergenService = new AllergenServiceImpl();
//		reportService = new ReportServiceImpl();
	}


	@Override
	public CategoryService getCategoryService() {
		// return categoryService;
		return applicationContext.getBean("categoryService", CategoryService.class);
	}


	@Override
	public AllergenService getAllergenService() {
		// return allergenService;
		return null;
	}


	@Override
	public ReportService getReportService() {
		// TODO cambia
		return new ReportServiceImpl();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
