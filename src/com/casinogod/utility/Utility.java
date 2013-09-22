package com.casinogod.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;


public class Utility {	
	
	private static Random rand = new Random(nowMilliSecondsLong());
	
	public static String randomString(int count) {
		return org.apache.commons.lang.RandomStringUtils.randomAlphanumeric(count);
	}
	
	public static String randomString() {
		
		StringBuffer sb = new StringBuffer(); 
	    
	    int hightPos, lowPos; // 瀹氫箟楂樹綆浣�
		Random random = new Random();
		
	    
	    for (int i = 0; i < 4; i++) {   		    	
	    	hightPos = (187 + Math.abs(random.nextInt(39)));// 鑾峰彇楂樹綅鍊�
			lowPos = (167 + Math.abs(random.nextInt(73)));// 鑾峰彇浣庝綅鍊�	    	
			byte[] b = new byte[2];
			b[0] = (new Integer(hightPos).byteValue());
			b[1] = (new Integer(lowPos).byteValue());

			String str=null;
			
			try {
				str = new String(b, "gbk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
			sb.append(str);   
	    }  
	    
	    return sb.toString();
	}
	
	public static String nowMilliSecondsString() {
		return String.valueOf(nowMilliSecondsLong());
	}
	
	public static long randomLong()
	{	
		
		Random rand=new Random();
		int s = rand.nextInt(1000000)%(1000000-1000+1) + 1000;
		
		return s;
	}
	
	public static long nowMilliSecondsLong() {
		
		Date date = new Date();
		
		return date.getTime();
	}
	
	public static String getNowString()
	{
		Date now = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
    	
    	return sdf.format(now);
	}
	
	
	public static String getDateString()
	{
		Date now = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ",Locale.CHINA);
    	
    	return sdf.format(now);
	}
	
	public static String getNowHours()
	{
		Date now = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss",Locale.CHINA);
    	
    	return sdf.format(now);
	}
	
	public static String getBeforeHourAgoString(int n)
	{
		
		String hoursAgoTime = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date d=new Date(); 
		
		d.setHours(d.getHours()-n);
		
		hoursAgoTime=sdf.format(d);
		
		return hoursAgoTime; 
	}
	
	public static boolean expiredTime(String startTime,String currentTime,long time)
	{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		try {
		
			Date beginDate = format.parse(startTime);
			
			Date endDate= format.parse(currentTime); 

			long day=endDate.getTime()-beginDate.getTime();
			
			if(day>=time)
				return true;
			
			return false;

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public static java.sql.Date getFromJS(String date) throws ParseException
	{
		
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);   
        
        Date buildsqlDate=(Date) format1.parse(date);
       
        java.sql.Date sqlDate=new java.sql.Date(buildsqlDate.getTime());
    	
        return sqlDate;
	}
	
	public static java.sql.Date changeDate() throws ParseException
	{
		
	    Date date=new Date();
//		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);  	
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
    	
        return sqlDate;
	}
	
	public static String trimAdvance(String source, String filter )
	{
		String result = "";
		for(String temp : source.split(filter))
		{
			result += temp;
		}
		return result;
	}
	
	public static TreeMap<String, String> changeStringToMap(String queryParam)
	{
		if(queryParam == null || queryParam.length() < 1)
		{
			return null;
		}
		
		TreeMap<String, String> map = new TreeMap<String, String>();

		for(String query : queryParam.split("&"))
		{
			map.put(query.split("=")[0], query.split("=")[1]);
		}
		
		return map;
	}
	
	public static HashMap<String, String> getHashMap(
			String content, String seperater) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		for(String seg : content.split(seperater))
		{
			if(seg.contains("="))
			{
				map.put(seg.split("=")[0], seg.split("=")[1]);
			}
			else
			{
				map.put(seg, "");
			}
		}
		return map;
	}
	
	public static String getJSONFromHash(HashMap source)
	{
		if(source.size() < 1)
		{
			return "";
		}
		
		String json = "{";
		
		for(Object obj : source.keySet().toArray())
		{
			json += "\"item_" + (String)obj + "\":\"" + (String)source.get(obj) + "\",";
		}
		json = json.substring(0,json.length()-1);
		json += "}";
		return json;		
	}
	
	public static int getMod(int sourceInt, int div)
	{
		int modValue = 0;
		
		modValue = (sourceInt - sourceInt % div) / div;
	
		return modValue;
	}
	
	public static int getExp(int n)
	{
		if(n==1) 
			
			return 50;
	
		else
			
			return getExp(n-1)+150*(n-1);
	}
	
	public static int getLevel(int exp)
	{
		int i=1;
		
		if(exp<=50) 	
			
			return i;
	
		else
		{
			while(i>0)
			{
				if(exp>=Utility.getExp(i)&&exp<Utility.getExp(i+1))
					break;
				else
					i++;
			}

		}
		
		return i;
	}
	
	public static boolean isDiff(String [] nums)
	{
		boolean flag=true;	
		
		for(int i=0;i<nums.length-1;i++)
		{
			for(int j=i+1; j<nums.length;j++)
			{
				if(nums[i].equals(nums[j]))
					flag=false;
			}
		}
		
		return flag;
	}
	
	public static String postdata (HttpServletRequest request) throws IOException
	{
		  System.out.println("getContentType===="+request.getContentType());
		  
		  //获取HTTP请求的输入流
	      //已HTTP请求输入流建立一个BufferedReader对象
	      
		  BufferedReader br =  request.getReader();
	      String buffer = null;
	      StringBuffer buff = new StringBuffer();
	      while ((buffer = br.readLine()) != null) {
	              buff.append(buffer+"\n");
	      }
	      br.close();

	    //  System.out.println("接收post发送数据:\n"+buff.toString());

	      return buff.toString().trim();

	}
	
	public static String splitString(String str,String temp){ 

		String result = null; 
		
		if (str.indexOf(temp) != -1) { 
		
			if (str.substring(str.indexOf(temp)).indexOf("&") != -1) { 
		
			
				result = str.substring(str.indexOf(temp)).substring(str.substring(str.indexOf(temp)).indexOf("=")+1, str.substring(str.indexOf(temp)).indexOf("&")); 
		
			} else { 
		
			result = str.substring(str.indexOf(temp)).substring(str.substring(str.indexOf(temp)).indexOf("=")+1); 
		} 

	
		} 
		
		return result; 

		} 

}
