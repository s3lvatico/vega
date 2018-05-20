package org.gmnz.sandbox;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class GSha1 {

	private final MessageDigest sha1digest;



	GSha1() {
		MessageDigest sha1digest1;
		try {
			sha1digest1 = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("warning - could not initialize the digester");
			sha1digest1 = null;
		}
		sha1digest = sha1digest1;
	}



	byte[] computeSha1(String s) {
		validateDigester();
		sha1digest.reset();
		byte[] digest = sha1digest.digest(s.getBytes());
		return digest;
	}



	String computeSha1AsString(String s) {
		byte[] digest = computeSha1(s);
		return "to be implemented";
	}



	private void validateDigester() {
		if (sha1digest == null) throw new UnsupportedOperationException("digester not initialized");
	}

}
