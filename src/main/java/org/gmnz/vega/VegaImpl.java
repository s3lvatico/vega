
package org.gmnz.vega;


import org.gmnz.vega.service.AllergenService;
import org.gmnz.vega.service.CategoryService;
import org.gmnz.vega.service.ReportService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class VegaImpl implements Vega, ApplicationContextAware {


	private ApplicationContext applicationContext;

	@Override
	public CategoryService getCategoryService() {
		return applicationContext.getBean("categoryService", CategoryService.class);
	}



	@Override
	public AllergenService getAllergenService() {
		return applicationContext.getBean("allergenService", AllergenService.class);
	}



	@Override
	public ReportService getReportService() {
		return applicationContext.getBean(ReportService.class);
	}



	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
