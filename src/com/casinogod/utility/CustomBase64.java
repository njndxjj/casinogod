package com.casinogod.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomBase64 {
	private static MessageDigest digest = null;
	
	public static String encode(String str) throws UnsupportedEncodingException{
		Base64Algo base = new Base64Algo();
		String md5 = MD52Hex(str).toUpperCase();

	//	System.out.println("md5 sign-->"+md5);
		String C1 = base.encode(str.getBytes("utf-8"));
	//	System.out.println("base 64 sign-->"+C1);
		StringBuffer C2 = new StringBuffer(C1.replace("=", "")).reverse();
	//	System.out.println("base 64 replace == -->"+C2.toString());
		int r = (int)(Math.random()*31);
		r = (r%2 == 0)?(r+1):r;
	//	System.out.println("random r -->"+r);
		String C3 = md5.substring(0,r) + C2 + md5.substring(r);
	//	System.out.println("C3 vaule -->"+C3);
		String K = base.encode(((r+9)+"").getBytes("utf-8")).replace("=", "");
	//	System.out.println("K vaule -->"+K);

		String C4 = C3 + K;
		return C4;
	}
	public static String decode(String str){
		Base64Algo base = new Base64Algo();
		String K = str.substring(str.length()-3);

	//	System.out.println("K vaule -->"+K);
		int r = Integer.valueOf(new String(base.decode(K+"="))) - 9;
	//	System.out.println("random r -->"+r);
		str = str.substring(0, str.length()-3);
	//	System.out.println("sub str -->"+str);
		String md5 = str.substring(0,r) + str.substring(str.length() - 32 + r);
	//	System.out.println("md5 -->"+md5);
		StringBuffer C2 = new StringBuffer(str.substring(r, str.length() - 32 + r)).reverse();
	//	System.out.println("C2-->"+C2.toString());
		int t = (4 - C2.length()%4)%4;
	//	System.out.println("t-->"+t);

		for(int i=0;i<t;i++) {
			C2.append("=");
		}
		String C1 = C2.toString();
		String ret = new String(base.decode(C1));


		System.out.println("ret-->"+ret);


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
		
//		CreateJson json1=new CreateJson();
//    	CreateJson json2=new CreateJson();
//    	CreateJson json3=new CreateJson();
//    	CreateJson json4=new CreateJson();
//    	CreateJson json5=new CreateJson();
//    	CreateJson json6=new CreateJson();
//    	
//    	json1.add("type", "WiFi");
//    	
//    	json3.add("time","627");
//    	json3.add("url","http:\\/\\/mbga.mbgadev.cn\\/_sdk_weak_chk_and_auth");
//    	
//    	json2.add("affcode", "1980000000");
//    	json2.add("gameid", "83000214");
//    	json2.add("userid", "411004781");
//    	
//    	json5.add("advertisingIdentifier", "FA8B774A1DC045A0A4A76B778B5461AB");
//    	json5.add("devicename", "iPhone4,1");
//    	json5.add("identifierForVendors", "411004781");
//    	json5.add("isJailBroken", "NO");
//    	json5.add("macaddress", "8485064232C5");
//    	json5.add("osversion", "6.1.2");
//    	
//    	json6.add("OS", "iOS");
//    	json6.add("gamever", "1.3.0");
//    	json6.add("sdktype", "ndk");
//    	json6.add("sdkver", "1.3.5");
//    	
//    	
//    	
//    	
//    	
//    	
//    	json4.add("apiperformance", json3);
//    	
////    	json4.add("commoninfo", json2.toString(false));
////    	
////    	json4.add("deviceinfo", json5.toString(false));
////    	
////    	json4.add("gameinfo", json6.toString(false));
////   
////    	json4.add("networkstatus", json1.toString(false));
////    	
////
////    	
////    	
////    	String plain=json4.toString(false);
		
		
		String cypher = decode("0DBB0BB4C69DE0XfwojIsVmdlxEcpZnIsIybClWZXJiOiUGc5RlclNXdiwCOwADMxojIklkclNXdiwiI2UDNzITMiojIl52boBXZsVGdiwiIw4CN1oDN1oTMxAyNx0SNw0yMxAjMiojIlRXYEJXZ0NXanVmciwiIiojIzVGdv5kclhGdvJCLiEWbhx2bhdmI6ISZtFmTrNWauJCL5EjOiwWZ2VGbiwSX9hDMwATM6ICZJJXZzVnIsETM6ISb15UblRXaiwiIoNGdpd3Uf1WZ0l0YpdWYNJiOiUWbh5UblRXaiwyM6ICZJ1WZ0lmIsMTM6ICZpJCLwojIlBXeUVWbhdmIsICmPWOmPWOmPW+p6eeh2iuI6ICduVWbt92YisHL9hDMwATM6ICZJJXZzVnIsYjOi0WdO1WZ0lmIsIyblRWaWJiOiUWbh5UblRXaiwSM6ICZJ1WZ0lmIsgjOiQWaiwCM6ISZwlHVl1WYnJCLiQ3clRnI6ICduVWbt92YisHL9hDMwATM6ICZJJXZzVnIsAzMxEjOi0WdO1WZ0lmIsIiclt2bQByblRWaWJiOiUWbh5UblRXaiwSM6ICZJ1WZ0lmIsUjOiQWaiwCM6ISZwlHVl1WYnJCLiIXZr9GUg8WZklmViojI05WZt12bjJyebpjIz1WZ0lmIsISMvATNzkTO1MjN2UzLwgTMvQTM0gDNyUDM2IzLuNmLn1Wah5Waz5yMwR3LvoDc0RHaiojIldWYtlmIsAzMxAjN6ICZs92ZiwiItJiOiIXZk5WZnJCLiMSMwADMxMyMwADMxIiOiQ3cpxEZuVWayZmIsgDM5cjM6ICc4VmIsISbvNmL0NXZ0BEdzVGdiojIzNXZyRGZBxWah1WZiwSMwYTO5cjOiQmbv1WYpRmIs0Vf7tlOiM3ajFmYsxWYjJye6Iybm5WayV2c1JCLiQndlRWZtx2VlNmVR9mZWdkI6Iiblt2b0JCLiQTM0gDNyUDM2IjI6ICZJNnbzJCLiQ2bH9mbpNXYD9CM4ADO6EjMy4yMuQjMx4iMxEzLvoDc0RHaiojIsJXVyVmdyV2Ul1WYnJye0B4C90F9A86EA40F568MjI");
		System.out.println("plain: " + cypher);
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
