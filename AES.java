import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

// AES - ADVANCED ENCRYPTION STANDARD

public class AES {
	private static byte[] keyValue = new byte[] { (byte) 0x25b2,
		(byte) 0x25bc, (byte) 0xb8, (byte) 0xbe, (byte) 0xaf, (byte) 0xd0,
		(byte) 0x255a, (byte) 0x2665, (byte) 0x2557, (byte) 0x253c,
		(byte) 0x2560, (byte) 0x256c, (byte) 0x2569, (byte) 0x2566,
		(byte) 0x2593, (byte) 0x2640 }; //any password of 16 byts.[default password]
	
	
	public AES() {}
	public AES(byte[] keyValue){ //Initiate with a 16bytes array
		AES.keyValue = keyValue;
	}
	
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, "AES");
		return key;
	}

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
}
