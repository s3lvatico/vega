package org.gmnz.vega.base;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * creato da simone in data 18/01/2018.
 */
public class VegaUtil {

	public static boolean stringNullOrEmpty(String s) {
		s = s == null ? "" : s.trim();
		return s.length() == 0;
	}



	public static String normalizeString(String s) {
		if (s == null)
			return "";
		else
			return s.trim();
	}



	public static MessageDigest createMessageDigest() {
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

}
