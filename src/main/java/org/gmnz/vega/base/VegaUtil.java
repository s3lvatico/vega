package org.gmnz.vega.base;


/**
 * creato da simone in data 18/01/2018.
 */
public class VegaUtil {

	public static boolean stringNullOrEmpty(String s) {
		s = s == null ? "" : s.trim();
		return s.length() == 0;
	}



	public static String normalizeString(String s) {
		if (s == null) return "";
		else return s.trim();
	}

}
