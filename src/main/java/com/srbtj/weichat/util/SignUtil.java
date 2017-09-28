package com.srbtj.weichat.util;

import java.security.MessageDigest;
import java.util.Arrays;


public class SignUtil {

	private static String token = "weichat";
	
	/**
	 *  ���ǩ��  ����  token, timestamp, nonce ͨ��������ת�����ַ����� ���� she1���� �� ��signature�Ա�   ��ͬ ����  true ���򷵻� false
	 * @param signature ΢�Ŵ���ǩ���ַ���
	 * @param nonestr  ����ַ���
	 * @param timestamp ʱ���
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		
		String[] arr = new String[]{token, timestamp, nonce};
		// �� token, timestamp, nonce �����ַ�������
		Arrays.sort(arr);
		// ���������ַ���ת�����ַ��� ������ sha1����
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
	 *  ���ֽ�����ת����ʮ�������ַ���
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
	 *  ���ֽ�ת����ʮ�������ַ���
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
