package com.casinogod.webinterface;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.json.JSONException;
import org.json.JSONObject;

import com.casinogod.utility.ConnectUtiltiy;
import com.casinogod.utility.CreateJson;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;


public class IAPServlet extends HttpServlet {
	
    
	private static String sandBox="https://sandbox.itunes.apple.com/verifyReceipt";
	
	private static String production="https://buy.itunes.apple.com/verifyReceipt";
	
	private static final String APPLICATION_JSON = "application/x-www-form-urlencoded";  
    
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	
	private static Logger log = Logger.getLogger(IAPServlet.class); 

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
	
	    doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			
		response.setContentType( "text/html;charset=UTF-8");
//
//		String [] receiptToken= request.getParameter("receipt").substring(1,request.getParameter("receipt").length()-1).split(" ");
		
		String  receiptToken= (String)request.getParameter("receipt");
		
		log.info("receiptToken -->" +  receiptToken);
				
		log.info("before base 64 "+receiptToken);
		
		
		String product_id=(String) request.getParameter("productId");
		
		int quantity=Integer.valueOf((String)request.getParameter("quantity"));
		
		boolean sandbox=Boolean.valueOf((String)request.getParameter("sandbox"));
				
		String success = null;
		
		CreateJson receiptJson=new CreateJson();
		
		receiptJson.add("receipt-data", receiptToken);
		
		String receipt=receiptJson.toString(true);
		
//		String receipt="{\"receipt-data\" : \""+ receiptBase + "\"}";
		
		log.info("receipt ->"+receipt);
				   
	    String responseJSON= ""; 
	        
	    try {
	    	   	
	     success=ConnectUtiltiy.send(receipt,sandbox?sandBox:production);
	     	
	     Thread.sleep(100);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	   try {
		
		   JSONObject receiptObject=new JSONObject(success);
		   
		   log.info("receiptObject :"+receiptObject.toString());
		   
		   int status=receiptObject.getInt("status");
		   
		   log.info("status vaule: " +status);
		   
		   if(status==0)
		   {
			   //valid receipt
			   
			   JSONObject receiptData=new JSONObject(receiptObject.getString("receipt"));
			   
			   log.info("receipt data: "+receiptData.toString());
			   
			   String appProductId=receiptData.getString("product_id");
			   int appQuantity=receiptData.getInt("quantity");
			   
			   log.info("app product:"+ appProductId);
			   log.info("app quantity :"+appQuantity);
			   
			   if(product_id.equals(appProductId)&&appQuantity==quantity)
			   {
				   // add diamond
				   			   
				   CreateJson prodcut=new CreateJson();
				   prodcut.add("statue", 0);
				   prodcut.add("product_id", product_id);
				   
				   responseJSON=prodcut.toString(false);
				   
				   response.setStatus(200);
			  
			   }
			   else
			   {
				   CreateJson prodcut=new CreateJson();
				   prodcut.add("statue", -1);
				   prodcut.add("product_id", "no such productionId");
				   responseJSON=prodcut.toString(false);
				   
				   response.setStatus(300);
			   }
			   
		   }
		   else
		   {
			   //invalid receipt
			   ErrorResponse errorResponse = new ErrorResponse();
			   errorResponse.setErrorMessage("inval receipt-data");
			   errorResponse.setErrorAction("");
			   errorResponse.setErrorCode(ErrorCode.purchase_Information);
				
			   responseJSON=net.sf.json.JSONObject.fromObject(errorResponse).toString();
			   
			   response.setStatus(401);			   
		   }
	 
	   } catch (JSONException e) {
		   
		   // TODO Auto-generated catch block
		   ErrorResponse errorResponse = new ErrorResponse();
		   errorResponse.setErrorMessage(e.getLocalizedMessage());
		   errorResponse.setErrorAction("");
		   errorResponse.setErrorCode(ErrorCode.purchase_Information);
		   responseJSON=net.sf.json.JSONObject.fromObject(errorResponse).toString();
		   response.setStatus(501);			 
	   }
	   
	   try {
//			responseJSON=CustomBase64.encode(responseJSON);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
		  e.printStackTrace();
		}
	   
	}
	
//	private String send(String json,String reqUrl) throws Exception {
//		
//	      DefaultHttpClient httpClient = new DefaultHttpClient();  
//	       
//	      HttpPost httpPost = new HttpPost(reqUrl);  
//	      httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);  
//	   // String encoderson = URLEncoder.encode(json, HTTP.UTF_8);  
//	      StringEntity se = new StringEntity(json);  
//	      se.setContentType(CONTENT_TYPE_TEXT_JSON);  
//	      se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));  
//	      httpPost.setEntity(se);
//	      
//	      log.info("reqUrl -->"+reqUrl);
//	      log.info("post data -->"+json);
//	      
//	      //返回服务器响应  	      
//	      HttpResponse response = httpClient.execute(httpPost);  
//	      HttpEntity   entity = response.getEntity();              
//	      
//	      try { 
//	    	  
//	    	  log.info("----------------------------------------");  
//	    	  log.info("服务器返回状态值 -->"+response.getStatusLine().getStatusCode()); // 服务器返回状态  
//	    	  Header[] headers = response.getAllHeaders(); // 返回的HTTP头信息  
//	          for (int i = 0; i < headers.length; i++) {  
//	        	  log.info("header message ->"+headers[i]);  
//	            }  
//	          String responseString = null;  
//	          if (response.getEntity() != null) {  
//	                responseString = EntityUtils.toString(response.getEntity()); // 返回服务器响应的HTML代码  
//	                log.info("result -->"+responseString); // 打印出服务器响应的HTML代码  
//	            } 
//	          return responseString;
//	       
//	      } finally {  
//	            if (entity != null)  
//	                entity.consumeContent(); // release connection gracefully  
//	        }  
//	      
//		
//	}


}
