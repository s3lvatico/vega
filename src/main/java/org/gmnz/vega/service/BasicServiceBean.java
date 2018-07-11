package org.gmnz.vega.service;


import org.gmnz.vega.VegaException;
import org.gmnz.vega.repository.DummyRepository;


class BasicServiceBean {

	protected void checkEntityRegistration(Class<?> clazz, String objectName, boolean mustBeInTheSystem)
			throws VegaException {

		boolean entityInTheSystem;
		switch (clazz.getSimpleName()) {
		case "Category":
			entityInTheSystem = DummyRepository.getCategoryByName(objectName) != null;
			break;
		case "Allergen":
			entityInTheSystem = DummyRepository.getAllergenByName(objectName) != null;
			break;
		default:
			throw new VegaException("anomalous condition occurred - cannot determine whether an entity is in the system");
		}
		if (mustBeInTheSystem ^ entityInTheSystem) {
			String errorMessage = String.format("%s [%s] was%s expected to be in the system but it is%s.",
					clazz.getSimpleName(), objectName, (mustBeInTheSystem ? "" : " not"), (entityInTheSystem ? "" : " not"));
			throw new VegaException(errorMessage);
		}
	}
	
}
