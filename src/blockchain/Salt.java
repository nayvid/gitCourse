package blockchain;

import java.security.SecureRandom;

public class Salt {

	public static byte[] generate() {
		//creating rand value...
		SecureRandom sr = new SecureRandom();
		byte[] b = new byte[16];
		sr.nextBytes(b);
		return b;
	}
}
