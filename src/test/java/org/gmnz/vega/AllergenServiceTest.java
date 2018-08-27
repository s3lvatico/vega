package org.gmnz.vega;


import org.gmnz.vega.service.AllergenService;
import org.junit.BeforeClass;
import org.junit.Test;


public class AllergenServiceTest {

	private static Vega vega;



	@BeforeClass
	public static void initApplicationContext() {
		VegaSpringUtil.initSpring();
		vega = VegaFactory.getFactory().buildVega();
	}



	@Test
	public void getAllAllergens() throws VegaException {
		AllergenService svc = vega.getAllergenService();
		System.out.println(svc.getAllAllergens());
	}



	@Test
	public void createAllergenWrongParams() throws VegaException {
		AllergenService svc = vega.getAllergenService();
		try {
			svc.createAllergen(null, "someId");
		} catch (VegaException e) {
			System.err.println("(null, someId) : " + e.getMessage());
		}
		try {
			svc.createAllergen("", "someId");
		} catch (VegaException e) {
			System.err.println("(empty, someId) : " + e.getMessage());
		}
		try {
			svc.createAllergen("someName", null);
		} catch (VegaException e) {
			System.err.println("(someName, null) : " + e.getMessage());
		}
		try {
			svc.createAllergen("someName", "");
		} catch (VegaException e) {
			System.err.println("(someName, empty) : " + e.getMessage());
		}
		try {
			svc.createAllergen(null, null);
		} catch (VegaException e) {
			System.err.println("(null, null) : " + e.getMessage());
		}
		try {
			svc.createAllergen(null, "");
		} catch (VegaException e) {
			System.err.println("(null, empty) : " + e.getMessage());
		}
		try {
			svc.createAllergen("", null);
		} catch (VegaException e) {
			System.err.println("(empty, null) : " + e.getMessage());
		}
		try {
			svc.createAllergen("", "");
		} catch (VegaException e) {
			System.err.println("(empty, empty) : " + e.getMessage());
		}
	}

}
