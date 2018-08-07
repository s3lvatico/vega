package org.gmnz.vega.base;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


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



	public static String getSha256Digest(String s1, Date date) {
		Long dateTimestamp = date.getTime();
		String s2 = Long.toString(dateTimestamp);
		MessageDigest md = createMessageDigest();
		md.update(s1.getBytes());
		byte[] digest = md.digest(s2.getBytes());

		StringBuilder sbDigest = new StringBuilder();
		for (byte b : digest) {
			sbDigest.append(String.format("%02x", (b & 0xff)));
		}
		return sbDigest.toString();
	}



	public static void main(String[] args) {
		String s1 = "oh ciao eh";
		Date d = new Date();
		String sha256 = getSha256Digest(s1, d);
		System.out.format("Stringa <%s> | Data <%s> | hash <%s>%n", s1, d, sha256);
		System.out.printf("Lunghezza hash : %d%n", sha256.length());
	}

}
