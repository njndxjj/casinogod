package com.casinogod.utility;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.casinogod.webinterface.IAPServlet;

public class ConnectUtiltiy {
	
	private static final String APPLICATION_JSON = "application/x-www-form-urlencoded";  
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    
    private static Logger log = Logger.getLogger(ConnectUtiltiy.class); 
	
	public static String send(String json,String reqUrl) throws Exception {
		
	      DefaultHttpClient httpClient = new DefaultHttpClient();  
	       
	      HttpPost httpPost = new HttpPost(reqUrl);  
	      httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);  
	   // String encoderson = URLEncoder.encode(json, HTTP.UTF_8);  
	      StringEntity se = new StringEntity(json);  
	      se.setContentType(CONTENT_TYPE_TEXT_JSON);  
	      se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));  
	      httpPost.setEntity(se);
	      
	      log.info("reqUrl -->"+reqUrl);
	      log.info("post data -->"+json);
	      
	      //���ط�������Ӧ  	      
	      HttpResponse response = httpClient.execute(httpPost);  
	      HttpEntity   entity = response.getEntity();              
	      
	      try { 
	    	  
	    	  log.info("----------------------------------------");  
	    	  log.info("����������״ֵ̬ -->"+response.getStatusLine().getStatusCode()); // ����������״̬  
	    	  Header[] headers = response.getAllHeaders(); // ���ص�HTTPͷ��Ϣ  
	          for (int i = 0; i < headers.length; i++) {  
	        	  log.info("header message ->"+headers[i]);  
	            }  
	          String responseString = null;  
	          if (response.getEntity() != null) {  
	                responseString = EntityUtils.toString(response.getEntity()); // ���ط�������Ӧ��HTML����  
	                log.info("result -->"+responseString); // ��ӡ����������Ӧ��HTML����  
	            } 
	          return responseString;
	       
	      } finally {  
	            if (entity != null)  
	                entity.consumeContent(); // release connection gracefully  
	        }  
	      
		
	}

}
