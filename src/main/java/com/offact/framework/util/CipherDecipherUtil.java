package com.offact.framework.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CipherDecipherUtil {
	
	/**
	 * 암호화
	 * @param input
	 * @param key
	 * @return
	 */
	public static String encrypt(String input, String key) {
		byte[] data = input.getBytes();
		byte[] keyBytes = key.getBytes();
		byte[] encrypted = null;

		try {
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			encrypted = cipher.doFinal(data);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return bin2hex(encrypted).toUpperCase();
	}

	/**
	 * 복호화
	 * @param input
	 * @param key
	 * @return
	 */
	public static String decrypt(String input, String key) {
		byte[] keyBytes = key.getBytes();
		byte[] output = null;

		try {
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			output = cipher.doFinal(hex2bin(input));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return new String(output);
	}

	private static String bin2hex(byte[] buf) {
		StringBuffer sb = new StringBuffer(buf.length * 2);
		for (int ix = 0; ix < buf.length; ix++) {
			if (((int) buf[ix] & 0xFF) < 0x10) {
				sb.append("0");
			}
			sb.append(Integer.toString((int) buf[ix] & 0xFF, 16));
		}
		return sb.toString();
	}

	public static byte[] hex2bin(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int index = 0; index < bytes.length; index++) {
			bytes[index] = (byte) Integer.parseInt(hex.substring(index * 2, (index + 1) * 2), 16);
		}
		return bytes;
	}
}
