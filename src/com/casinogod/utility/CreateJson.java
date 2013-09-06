package com.casinogod.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class CreateJson {
	
	private Map map = new LinkedHashMap();
	 
	     /**
	     * 锟斤拷锟揭伙拷锟�JSON 锟斤拷锟皆ｏ拷值为一锟斤拷锟街凤拷锟截革拷锟斤拷锟绞憋拷锟斤拷锟斤拷锟斤拷锟�p/>
	      * 
	      * add("name", "value");<br/>
	      * 锟斤拷锟揭伙拷锟斤拷址锟斤拷锟斤拷锟斤拷 JSON 锟界：{"name":"value"}<p/>
	      * 
	      * add("name", "value1");<br/>
	      * add("name", "value2");<br/>
	      * 锟斤拷锟斤拷锟斤拷锟酵拷锟斤拷缘锟斤拷址锟斤拷锟斤拷锟斤拷 JSON 锟界：{"name":["value1", "value2"]}<p/>
	      * 
	      * @param key       JSON 锟斤拷锟斤拷锟斤拷
	       * @param str       锟街凤拷锟绞斤拷锟斤拷锟斤拷锟街�
	       */
	     public void add(String key, String value) {
	         addElement(key, value);
	     }
	     
	     public void add(String key, int num) {
	        addElement(key, new Integer(num));
	     }
	     
	     public void add(String key, boolean b) {
	         addElement(key, new Boolean(b));
	     }
	 
	     /**
	      * 锟斤拷锟揭伙拷锟�JSON 锟斤拷锟皆ｏ拷值为一锟斤拷 JSON锟斤拷锟截革拷锟斤拷锟绞憋拷锟斤拷锟�JSON 锟斤拷锟斤拷<p/>
	      * 
	      * Json json1 = new Json();<br/>
	      * json1.add("name1", "value1");<br/>
	     * json1.add("name2", "value2");<br/>
	      * Json json = new Json();<br/>
	      * json.add("message", json1);<br/>
	      * 锟斤拷锟揭伙拷锟�JSON锟斤拷锟斤拷锟斤拷锟�JSON 锟界：{"message":{"name1":"value1", "name2":"value2"}}<p/>
	      * 
	      * Json json1 = new Json();<br/>
	      * json1.add("name1", "value1");<br/>
	      * json1.add("name2", "value2");<br/>
	      * Json json2 = new Json();<br/>
	      * json2.add("name1", "value3");<br/>
	      * json2.add("name2", "value4");<br/>
	      * Json json = new Json();<br/>
	      * json.add("message", json1);<br/>
	      * json.add("message", json2);<br/>
	      * 锟斤拷锟斤拷锟斤拷锟酵拷锟斤拷缘锟�JSON锟斤拷锟斤拷锟斤拷锟�JSON 锟界：{"message":[{"name1":"value1", "name2":"value2"}, {"name1":"value3", "name2":"value4"}]}<p/>
	      * 
	      * @param key       JSON 锟斤拷锟斤拷锟斤拷
	       * @param json      JSON 锟斤拷式锟斤拷锟斤拷锟斤拷值
	       */
	     public void add(String key, CreateJson json) {
	         addElement(key, json);
	     }
	 
	     public String toString(boolean flag) {
	    	 StringBuilder sb = new StringBuilder();
	         sb.append("{\n\t");
	         int k = 0;
	         for (Iterator i = map.keySet().iterator(); i.hasNext();) {
	             String key = (String)(i.next());
	             Object obj = map.get(key);
	             if (k > 0) {
	                 sb.append(",\n\t");
	             }
	             appendKey(sb, key);
	             if (obj instanceof String) {
	                appendString(sb, (String)obj,flag);
	            } else if (obj instanceof List) {
	                appendList(sb, (List)obj,flag);
	            } else if (obj instanceof CreateJson) {
	                appendJson(sb, (CreateJson)obj);
	            } else {
	                appendOther(sb, obj);
	            }
	            k++;
	        }
	        sb.append("\n}");
	      return sb.toString();
	     }
	     
	     public String normalToString(boolean flag) {
	    	 StringBuilder sb = new StringBuilder();
	         sb.append("{");
	         int k = 0;
	         for (Iterator i = map.keySet().iterator(); i.hasNext();) {
	             String key = (String)(i.next());
	             Object obj = map.get(key);
	             if (k > 0) {
	                 sb.append(",");
	             }
	             appendKey(sb, key);
	             if (obj instanceof String) {
	                appendString(sb, (String)obj,flag);
	            } else if (obj instanceof List) {
	                appendList(sb, (List)obj,flag);
	            } else if (obj instanceof CreateJson) {
	                appendJson(sb, (CreateJson)obj);
	            } else {
	                appendOther(sb, obj);
	            }
	            k++;
	        }
	        sb.append("}");
	      return sb.toString();
	     }
	     
	    private void addElement(String key, Object obj) {
	        if (!map.containsKey(key)) {
	            if(obj instanceof CreateJson) {
	                List list = new ArrayList();
	                list.add(obj);
	                map.put(key, list);
	            } else {
	                map.put(key, obj);
	           }
	            return;
	        }
	
	        Object o = map.remove(key);
	
	        if (o instanceof List) {
	            ((List)o).add(obj);
	            map.put(key, o);
	            return;
	        }
	        // o is a String
	        List list = new ArrayList();
	        list.add(o);
	        list.add(obj);
	        map.put(key, list);
	    }
	
	    /**
	     * Append JSON property name
	    * 
	     * @param sb
	     * @param key
	     */
	    private void appendKey(StringBuilder sb, String key) {
	        sb.append("\"").append(key).append("\":");
	    }
	    
	    /**
	     * Append JSON property value that is a String
	     * 
	    * @param sb
	     * @param str
	     */
	    private void appendString(StringBuilder sb, String str,boolean flag) {
	        if(flag)
	        	sb.append("\"").append(str).append("\"");
	        else
	        	sb.append(str);
	    }
	    
	    /**
	     * Append JSON property value that is a Integer
	     * 
	     * @param sb
	     * @param num
	     */
	    private void appendOther(StringBuilder sb, Object obj) {
	        sb.append(obj);
	    }
	
	    /**
	     * Append JSON property value that is a List
	     * 
	     * @param sb
	     * @param list
	     */
	    private void appendList(StringBuilder sb, List list,boolean flag) {
	        sb.append("[");
	        for (int j = 0, m = list.size(); j < m; j++) {
	            if (j > 0) {
	                sb.append(",");
	            }
	            Object obj = list.get(j);
	            if (obj instanceof String) {
	                appendString(sb, (String)obj,flag);
	            } else if (obj instanceof CreateJson) {
	                appendJson(sb, (CreateJson)obj);
	            } else {
	                appendOther(sb, obj);
	            }
	        }
	        sb.append("]");
	    }
	
	    /**
	     * Append JSON property value that is a JSON
	     * 
	     * @param sb
	     * @param json
     */
	    private void appendJson(StringBuilder sb, CreateJson json) {
	        sb.append(json.toString(true));
	    }
	    
	    
	    /**
	     * {

                  "apiperformance":
			  [
			   {
				   "time":"627",
				   "url":"http:\/\/mbga.mbgadev.cn\/_sdk_weak_chk_and_auth"
			   }
			  ],
		     "commoninfo":
		      {
			       "affcode":"1980000000",
			       "gameid":"83000214",
			        "userid":"411004781"
		      },
		     "deviceinfo":
		      { 
				  "advertisingIdentifier":"FA8B774A1DC045A0A4A76B778B5461AB",
				  "devicename":"iPhone4,1",
				  "identifierForVendors":"C11675EE5843402F936582550E9EA274",
				   "isJailBroken":"NO",
				   "macaddress":"8485064232C5","osversion":"6.1.2"
		       },
		      "gameinfo":
		       {
		    	    "OS":"iOS",
		    	    "gamever":"1.3.0",
		    	    "sdktype":"ndk","sdkver":"1.3.5"
		    	},
		     "networkstatus":
		       {
		    		"type":"WiFi"
		       }
}
	     * @param args
	     */
	    
	    public static void  main(String args[])
	    {
	    	CreateJson json1=new CreateJson();
	    	
	    	
	    	json1.add("type", "WiFi");
	    	
	    	json1.add("time","627");
//	    	json3.add("url","http:\\/\\/mbga.mbgadev.cn\\/_sdk_weak_chk_and_auth");
//	    	
//	    	json1.add("affcode", "1980000000");
//	    	json2.add("gameid", "83000214");
//	    	json2.add("userid", "411004781");
//	    	
//	    	json5.add("advertisingIdentifier", "FA8B774A1DC045A0A4A76B778B5461AB");
//	    	json5.add("devicename", "iPhone4,1");
//	    	json5.add("identifierForVendors", "411004781");
//	    	json5.add("isJailBroken", "NO");
//	    	json5.add("macaddress", "8485064232C5");
//	    	json5.add("osversion", "6.1.2");
//	    	
//	    	json6.add("OS", "iOS");
//	    	json6.add("gamever", "1.3.0");
//	    	json6.add("sdktype", "ndk");
//	    	json6.add("sdkver", "1.3.5");
//	    	
//	    	
//	    	
//	    	
//	    	
//	    	
//	    	json4.add("apiperformance", json3);
//	    	
//	    	json4.add("commoninfo", json2.toString(false));
//	    	
//	    	json4.add("deviceinfo", json5.toString(false));
//	    	
//	    	json4.add("gameinfo", json6.toString(false));
//	   
//	    	json4.add("networkstatus", json1.toString(false));
//	    	

	    	
	    	
	    	String text1=json1.normalToString(false);
	    	
//	    	String text2=json2.toString();
	    	
	    	System.out.println(text1);
	    	
	    	try {
				
	    		JSONObject js=new JSONObject(text1);
				System.out.println("type:"+js.get("type"));
//				System.out.println("affcoed:"+js.get("affcode"));
			
	    	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
//	    	System.out.println(text2);
	    	
	    }

}
