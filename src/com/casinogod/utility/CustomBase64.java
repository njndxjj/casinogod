package com.casinogod.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomBase64 {
	private static MessageDigest digest = null;
	
	public static String encode(String str) throws UnsupportedEncodingException{
		Base64Algo base = new Base64Algo();
		String md5 = MD52Hex(str).toUpperCase();
		String C1 = base.encode(str.getBytes("utf-8"));
		StringBuffer C2 = new StringBuffer(C1.replace("=", "")).reverse();
		int r = (int)(Math.random()*31);
		r = (r%2 == 0)?(r+1):r;
		System.out.println(r);
		String C3 = md5.substring(0,r) + C2 + md5.substring(r);
		String K = base.encode(((r+9)+"").getBytes("utf-8")).replace("=", "");
		String C4 = C3 + K;
		return C4;
	}
	public static String decode(String str){
		Base64Algo base = new Base64Algo();
		String K = str.substring(str.length()-3);
		System.out.println(K);
		int r = Integer.valueOf(new String(base.decode(K+"="))) - 9;
		
		str = str.substring(0, str.length()-3);
		String md5 = str.substring(0,r) + str.substring(str.length() - 32 + r);
		StringBuffer C2 = new StringBuffer(str.substring(r, str.length() - 32 + r)).reverse();
		int t = (4 - C2.length()%4)%4;
		for(int i=0;i<t;i++) {
			C2.append("=");
		}
		String C1 = C2.toString();
		String ret = new String(base.decode(C1));
//		System.out.println(md5);
//		System.out.println(MD52Hex(ret).toUpperCase());
		
		if(!md5.equals(MD52Hex(ret).toUpperCase())) {
			return null;
		}
		return ret;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		Base64Algo base = new Base64Algo();
		//String plain="text";
		
		CreateJson json1=new CreateJson();
    	CreateJson json2=new CreateJson();
    	CreateJson json3=new CreateJson();
    	CreateJson json4=new CreateJson();
    	CreateJson json5=new CreateJson();
    	CreateJson json6=new CreateJson();
    	
    	json1.add("type", "WiFi");
    	
    	json3.add("time","627");
    	json3.add("url","http:\\/\\/mbga.mbgadev.cn\\/_sdk_weak_chk_and_auth");
    	
    	json2.add("affcode", "1980000000");
    	json2.add("gameid", "83000214");
    	json2.add("userid", "411004781");
    	
    	json5.add("advertisingIdentifier", "FA8B774A1DC045A0A4A76B778B5461AB");
    	json5.add("devicename", "iPhone4,1");
    	json5.add("identifierForVendors", "411004781");
    	json5.add("isJailBroken", "NO");
    	json5.add("macaddress", "8485064232C5");
    	json5.add("osversion", "6.1.2");
    	
    	json6.add("OS", "iOS");
    	json6.add("gamever", "1.3.0");
    	json6.add("sdktype", "ndk");
    	json6.add("sdkver", "1.3.5");
    	
    	
    	
    	
    	
    	
    	json4.add("apiperformance", json3);
    	
//    	json4.add("commoninfo", json2.toString(false));
//    	
//    	json4.add("deviceinfo", json5.toString(false));
//    	
//    	json4.add("gameinfo", json6.toString(false));
//   
//    	json4.add("networkstatus", json1.toString(false));
//    	
//
//    	
//    	
//    	String plain=json4.toString(false);
		
		
//		String cypher = encode(plain);
//		System.out.println("cypher: " + cypher);
//		System.out.println("Text: " + decode(cypher));
	}
	
	public synchronized static final String MD52Hex(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		try {
			digest.update(data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encodeHex(digest.digest());
	}
	
	private final static String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;
		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		//return buf.toString().toUpperCase();
		return buf.toString();
	}
	
	
}
