package com.casinogod.utility;

import org.json.JSONException;
import org.json.JSONObject;

public class Testjson {
	
	public static void main(String args[])
	{
//		
//		CreateJson json1=new CreateJson();
//    	
//    	json1.add("type", "WiFi");
//    	
//    	String text1=json1.toString(false);
//    	
//    	
//    	System.out.println(text1);
//    	
//    	try {
//			
//    		JSONObject js=new JSONObject(text1);
//			System.out.println("type:"+js.get("type"));
//		
//    	} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//	 String configName = "lottery-config";
		//	 GameConfig config = new GameConfig(configName);
		
		String test="userType=2&snsId=2605248414";
			
			System.out.println("testdata-->"+Utility.splitString(test, "snsId"));
		
		
		
	}

}
