package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.repository.AllergenDao;
import org.gmnz.vega.repository.CategoryDao;
import org.gmnz.vega.repository.ConnectionOrientedDao;
import org.gmnz.vega.repository.DaoException;
import org.gmnz.vega.repository.DaoFactory;


class BasicServiceBean {

	protected void checkEntityRegistration(Class<?> clazz, String objectName, boolean mustBeInTheSystem)
			throws VegaException {

		boolean entityIsInTheSystem;
		switch (clazz.getSimpleName()) {
		case "Category":
			entityIsInTheSystem = checkForCategory(objectName);
			break;
		case "Allergen":
			entityIsInTheSystem = checkForAllergen(objectName);
			break;
		default:
			StringBuilder sbError = new StringBuilder("anomalous condition occurred - ");
			sbError.append(String.format("cannot determine whether the entity [%s / %s] is in the system or not",
					objectName, clazz.getSimpleName()));
			throw new VegaException(sbError.toString());
		}
		if (mustBeInTheSystem ^ entityIsInTheSystem) {
			String errorMessage = String.format("%s [%s] was%s expected to be in the system but it is%s.",
					clazz.getSimpleName(), objectName, (mustBeInTheSystem ? "" : " not"),
					(entityIsInTheSystem ? "" : " not"));
			throw new VegaException(errorMessage);
		}
	}



	private boolean checkForCategory(String categoryName) throws VegaException {
		CategoryDao dao = null;
		try {
			dao = DaoFactory.getInstance().createCategoryDao();
			return dao.isCategoryRegisteredByName(categoryName);
		} catch (DaoException e) {
			e.printStackTrace();
			String errorMessage = String.format(
					"unable to check the presence of the category [%s] - exception was thrown by data layer", categoryName);
			throw new VegaException(errorMessage, e);
		} finally {
			finalizeDao(dao);
		}
	}



	private boolean checkForAllergen(String allergenName) throws VegaException {
		AllergenDao dao = null;
		try {
			dao = DaoFactory.getInstance().createAllergenDao();
			return dao.isAllergenRegisteredByName(allergenName);
		} catch (DaoException e) {
			e.printStackTrace();
			String errorMessage = String.format(
					"unable to check the presence of the allergen [%s] - exception was thrown by data layer", allergenName);
			throw new VegaException(errorMessage, e);
		} finally {
			finalizeDao(dao);
		}
	}



	protected void finalizeDao(ConnectionOrientedDao dao) {
		if (dao != null) {
			dao.close();
		}
	}

}
