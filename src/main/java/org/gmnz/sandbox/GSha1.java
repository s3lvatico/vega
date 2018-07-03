package org.gmnz.sandbox;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


class GSha1 {

	private final MessageDigest sha1digest;




	GSha1() {
		MessageDigest sha1digest1;
		try {
			sha1digest1 = MessageDigest.getInstance("SHA1");
		}
		catch (NoSuchAlgorithmException e) {
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
		StringBuilder sbDigest = new StringBuilder();
		for (byte b : digest) {
			// int n = (int) (b & 0xff);
			sbDigest.append(String.format("%02x", b & 0xff));
		}
		return sbDigest.toString();
	}




	private void validateDigester() {
		if (sha1digest == null)
			throw new UnsupportedOperationException("digester not initialized");
	}




	public static void main(String[] args) {
		GSha1 sha1 = new GSha1();
		String c64Sha = sha1.computeSha1AsString("c64");
		// b865271afeded8795c19b5a63716c3d16e6ffca3
		System.out.println(c64Sha);
		// b865271afeded8795c19b5a63716c3d16e6ffca3
	}

}
