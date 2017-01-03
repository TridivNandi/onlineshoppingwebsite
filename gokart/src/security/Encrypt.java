package security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Encrypt {
	private static transient Encrypt encrypt = new Encrypt();
			
	private Encrypt() {
	}
			
	public synchronized String hash(String algo, String data, boolean rawOutput) throws SystemUnavailableException {
		MessageDigest md = null;
			
		try{
			md = MessageDigest.getInstance(algo);
		}
		catch(NoSuchAlgorithmException e) {
			throw new SystemUnavailableException(e.getMessage());
		}
			
		try {
			md.update(data.getBytes("UTF-8"));
		}
		catch(UnsupportedEncodingException e) {
			throw new SystemUnavailableException(e.getMessage());
		}
		
		byte raw[] = md.digest();
		
		if (rawOutput)
			return new String(raw);		
		
		StringBuffer hexString = new StringBuffer();
		for (byte b : raw)
			hexString.append(String.format("%1$02x", 0xFF & b));
					
		return hexString.toString();
	}
			
	public static synchronized Encrypt getInstance() {	
				return encrypt;
			}
			
	public static void main(String[] args) throws SystemUnavailableException {
		System.out.println(Encrypt.getInstance().hash("SHA-512", "123456", true).length());
		System.out.println(Encrypt.getInstance().hash("SHA-1", "123456", false).length());
		if (Encrypt.getInstance() == Encrypt.getInstance())
			System.out.println("equal");
		else
			System.out.println("unequal");
	}
}