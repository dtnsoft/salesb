package com.offact.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.offact.framework.constants.SPErrors;
import com.offact.framework.exception.BizException;

public class SPUtils {
	
	private SPUtils() {}
	
	public static IvParameterSpec iv = new IvParameterSpec(new byte[] { 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
	
	public static String getToday() {
		return DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
	}
	
	public static String getMonth() {
		return DateFormatUtils.format(System.currentTimeMillis(), "yyyyMM");
	}
	
	public static String getCurrentTime() {
		return DateFormatUtils.format(System.currentTimeMillis(), "HHmmss");
	}
	
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isEmpty(List list) {
		return (list == null || list.size() == 0) ? true : false;
	}
		
	public static String encrypt(String keyStr, String plain)
			throws BizException {
		
		Cipher cipher = getCipher(keyStr, plain);
		Key key = generateKey("AES", keyStr.getBytes());
		
		try {
			
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			byte[] enc = cipher.doFinal(plain.getBytes("utf-8"));
			
			return toHexString(enc);
			
		} catch (InvalidKeyException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (IllegalBlockSizeException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (BadPaddingException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (InvalidAlgorithmParameterException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (UnsupportedEncodingException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		}
	}
	
	public static String decrypt(String keyStr, String encrypted)
			throws BizException {
		
		Cipher cipher = getCipher(keyStr, encrypted);
		Key key = generateKey("AES", keyStr.getBytes());
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] dec = cipher.doFinal(toBytes(encrypted,16));
			
			return new String(dec, "utf-8");
			
		} catch (InvalidKeyException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (IllegalBlockSizeException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (BadPaddingException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (UnsupportedEncodingException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (InvalidAlgorithmParameterException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		}
	}
	
	public static String genTempPwd() throws BizException {
		
		String key = UUID.randomUUID().toString();
		StringBuffer sb = new StringBuffer();
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(key.getBytes());
			byte[] md5bytes = md.digest();
			
	
			for (int i = 0; i < md5bytes.length; i++) {
				sb.append(Integer.toHexString((md5bytes[i] & 0xff))
						.substring(1));
			}
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
		return sb.substring(0,7);
	}
	
	private static Cipher getCipher(String keyStr, String plain)
			throws BizException {

		String transform = "AES/CBC/PKCS5Padding";
		try {
			return Cipher.getInstance(transform);
		} catch (NoSuchAlgorithmException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		} catch (NoSuchPaddingException e) {
			throw new BizException(SPErrors.CRYPTO_ERROR);
		}
	}
	
	private static String toHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
			result.append(Integer.toString((b & 0xF0) >> 4, 16));
			result.append(Integer.toString(b & 0x0F, 16));
		}
		return result.toString();
	}
	
	public static byte[] toBytes(String digits, int radix)
			throws IllegalArgumentException, NumberFormatException {

		if (digits == null) {
			return null;
		}
		if (radix != 16 && radix != 10 && radix != 8) {
			throw new IllegalArgumentException("For input radix: \"" + radix + "\"");
		}
		int divLen = (radix == 16) ? 2 : 3;
    	int length = digits.length();
    	if (length % divLen == 1) {
    		throw new IllegalArgumentException("For input string: \"" + digits + "\"");
    	}
    	length = length / divLen;
    	byte[] bytes = new byte[length];
    	for (int i = 0; i < length; i++) {
    		int index = i * divLen;
    		bytes[i] = (byte)(Short.parseShort(digits.substring(index, index+divLen), radix));
    	}
    	return bytes;
	}
	
	private static Key generateKey(String algorithm, byte[] key) {
		
//		128
		byte[] key128 = new byte[16];
		int length = key.length;
		if (length>=16) {
			System.arraycopy(key, 0, key128, 0, 16);
		}
		else {
			System.arraycopy(key, 0, key128, 0, length);
			for (int i=0;i<(16-length); i++) {
				key128[length+i]=key[i%length];
			}
		}
		
		SecretKeySpec keySpec = new SecretKeySpec(key128, algorithm);
		return keySpec;
	}
	
	/**
	 * Null 을 Empty 로 변경 (nvlToEmpty)
	 * Null 을 reqStr로 변경 (nvl)
	 * @param param
	 * @return
	 */
	
	public static String nvlToEmpty(String param){

		if(null == param){return "";}

		return param ; 

	}

	private static String nvl(String param,String reqStr){

		if(param == null){
			return reqStr ;
		}
		return param ; 
	}
	
	/**
	 * 쿼리 조회 시 Paging Limit 값 계산
	 * @param curPage - 현재페이지
	 * @param rowCount - 보여질 로우 갯수
	 * @return
	 */
	public static Integer calculateLimit(String curPage, String rowCount) {
		return calculateLimit(Integer.parseInt(curPage), Integer.parseInt(rowCount));
	}
	
	/**
	 * 쿼리 조회 시 Paging Limit 값 계산
	 * @param curPage - 현재페이지
	 * @param rowCount - 보여질 로우 갯수
	 * @return
	 */
	public static Integer calculateLimit(int curPage, int rowCount) {
		int limitStart = (curPage-1) * rowCount;
		return new Integer(limitStart);
	}
	
}
