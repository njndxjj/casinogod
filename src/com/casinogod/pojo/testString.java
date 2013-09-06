package com.casinogod.pojo;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import com.casinogod.battle.GameType;
import com.casinogod.utility.Utility;

public class testString {
	public static void main(String args[]) throws UnsupportedEncodingException, JSONException
	{
//		String beginTime="<f851adc6 693fa022 38f1acf5 9ec35e36 cec5041d 099bf8ef a3aeec1f 317a5423>";
//		String []token=beginTime.substring(1, beginTime.length()-2).split(" ");
//		StringBuffer device =new StringBuffer();
//		for(String test:token)
//			device.append(test);
//		
//		System.out.println("device "+device);
		
//		String endTime="2013-04-22";
//		String nowTime=Utility.getNowString();
//		System.out.println(nowTime.split(" ")[0]);
//		String date=nowTime.split(" ")[0];
//		int i=date.compareToIgnoreCase(beginTime);		
//		System.out.println("i is "+i);
//		Random rand=new Random();
//		int s = rand.nextInt(10000)%(10000-1000+1) + 1000;
//		System.out.println("s is "+s);
//		Random rand=new Random();
//		int s = rand.nextInt(100000)%(100000-1000+1) + 1000;
//		String nickName=Utility.randomString(s);
//		int a=0-20;
//		int b=30;
//		System.out.println(a+b);
//		 String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
//		    Random random = new Random();   
		    
//		StringBuffer sb = new StringBuffer(); 
//		    
//		    int hightPos, lowPos; // 瀹氫箟楂樹綆浣�
//			Random random = new Random();
//			
//		    
//		    for (int i = 0; i < 4; i++) {   		    	
//		    	hightPos = (187 + Math.abs(random.nextInt(39)));// 鑾峰彇楂樹綅鍊�
//				lowPos = (167 + Math.abs(random.nextInt(73)));// 鑾峰彇浣庝綅鍊�	    	
//				byte[] b = new byte[2];
//				b[0] = (new Integer(hightPos).byteValue());
//				b[1] = (new Integer(lowPos).byteValue());
//
//				String str = new String(b, "gbk"); 
//		        
//				sb.append(str);   
//		    }  
//		    
//		    
//		    System.out.println(sb.toString()); 
//
//		
//
//		 String result="2013-05-29?17:03:20";
//		Random random=new Random();
		
//		String currentTime = Utility.getNowString();
//		
//		String beforeTime=Utility.getBeforeHourAgoString(1);
//		
//		String hours=Utility.getNowHours();
//		
//		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); 

//		try {
		
//			Date beginDate = format.parse(hours);
//			
//			Date endDate= format.parse("22:04:22"); 	
			
//			String date="2013-06-13 00:00:00";

//			long day=beginDate.getDate()-endDate.getTime();
			
//		 org.json.JSONObject data;
//        
//         int discount=0;
//        
//        	
//        	data=new org.json.JSONObject("{\"bossType\": 1}");
//        	
//        	discount=data.getInt("BossType");

//		System.out.println(beginDate.getHours()+":"+beginDate.getMinutes()+":"+beginDate.getSeconds()); 
		
//		System.out.println(discount); 
		
	//	String nowTime=Utility.getNowString();
		
	//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		
		
//		Calendar day=Calendar.getInstance();
//		
//		day.add(Calendar.DATE, -1);
		
		
		
		System.out.println("time ->>"+GameType.values()[0].toString());
		
		
		
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
//		String startTime="2013-06-13 00:00:00";
//		
//		String endTime="2013-06-12 23:59:59";
//		
//        String currentTime=Utility.getNowString();
//
//		System.out.println(currentTime.compareTo(startTime)>0&&currentTime.compareTo(endTime)>0?true:false);
		
		
//		System.out.println("URLDecode -->"+currentTime +" before time -->"+beforeTime);
		
//		System.out.println("exp-->"+Utility.getExp(5));
//		
//		System.out.println(Utility.getLevel(38450));
	}

}
