package org.gmnz.sandbox;


public class Digests {

	void testDigests(String s) {
		GSha1 sha1 = new GSha1();
		byte[] digest = sha1.computeSha1(s);
		System.out.println(digest);
	}



	public static void main(String[] args) {
		Digests d = new Digests();
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		d.testDigests("porchetta de Costano");
		byte[] arr = {-4, -3, -2, -1, 0, 2, 5, 13};
		for (byte b : arr) {
			int dec = (int) (b & 0xff);
			System.out.printf("%s%s%n", hexDigits[dec/16], hexDigits[dec % 16]);
			System.out.printf("%02x%n", dec);
		}

	}
}
