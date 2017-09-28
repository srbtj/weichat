package com.srbtj.weichat.util;

import java.security.MessageDigest;
import java.util.Arrays;


public class SignUtil {

	private static String token = "weichat";
	
	/**
	 *  检查签名  根据  token, timestamp, nonce 通过排名，转换成字符串， 经过 she1加密 后 与signature对比   相同 返回  true 否则返回 false
	 * @param signature 微信传的签名字符串
	 * @param nonestr  随机字符串
	 * @param timestamp 时间戳
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		
		String[] arr = new String[]{token, timestamp, nonce};
		// 将 token, timestamp, nonce 进行字符串排序
		Arrays.sort(arr);
		// 将排序后的字符串转换成字符串 并进行 sha1加密
		StringBuilder sb = new StringBuilder();
		for(int i=0,len=arr.length; i<len; i++){
			sb.append(arr[i]);
		}
		
		MessageDigest msg = null;
		String tempStr = null;
		
		try {
			msg = MessageDigest.getInstance("SHA-1");
			byte[] digest = msg.digest(sb.toString().getBytes());
			
			tempStr = byteToString(digest);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		sb = null;
		
		return tempStr != null ? tempStr.equals(signature) : false;
	}
	
	/**
	 *  将字节数组转换成十六进制字符串
	 * @param byteArr
	 * @return
	 */
	private static String byteToString(byte[] byteArr){
		
		String strDigest = "";
		
		for(int i=0, len=byteArr.length; i<len; i++){
			strDigest += byteToHexStr(byteArr[i]);
		}
		return strDigest;
	}
	
	/**
	 *  将字节转换成十六进制字符串
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte){
		
		char[] Digit = {'0','1','2','3','4','5','6','7','8','9'};
		char[] tempChar = new char[2];
		tempChar[0] = Digit[(mByte >>> 4) & 0X0F];
		tempChar[2] = Digit[mByte & 0X0F];
		String s = new String(tempChar);
		
		return s;
	}
}
