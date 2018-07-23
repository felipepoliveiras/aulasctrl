package io.senai.aulasctrl.utils;

import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
	
	private static final String HASH_SALT = "skjdhsad08s7duan0sad9usdi0asudnsad9asdasdjasdjklasha9sd80asd98)(*sdisadijd";
	private HashUtils() {}
	
	private static MessageDigest getSha256MessageDigest() {
		final String alg = "SHA-256";
		try {
			return MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new InvalidParameterException("Invalid algorithm: " + alg);
		}
	}
	
	private static String buildStringFromMessageDigester(MessageDigest messageDigest, String msg) {
		messageDigest.update(msg.getBytes());
		byte[] digestedMessage = messageDigest.digest();
		
		StringBuffer strBuf = new StringBuffer();
		
		for (byte charByte : digestedMessage) {
			strBuf.append(String.format("%02x", charByte & 0xff));	
		}
		
		return strBuf.toString();
	}
	
	public static String saltedSha256(String msg) {
		return buildStringFromMessageDigester(getSha256MessageDigest(), HASH_SALT + msg);
	}

}
