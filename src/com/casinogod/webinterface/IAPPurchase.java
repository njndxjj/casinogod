package com.casinogod.webinterface;



import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.casinogod.pojo.Product;
import com.casinogod.pojo.User;
import com.casinogod.service.IAPLogService;
import com.casinogod.service.ProductService;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.ConnectUtiltiy;
import com.casinogod.utility.CreateJson;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class IAPPurchase extends ActionSupport implements ServletResponseAware {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String account;
    
    private String receipt;
    
    private String productId;
    
    private String quantity;
    
    private String sandbox;
    
    
	private UserProfileService userProfileService;
	
	private ProductService productService;
	
	private IAPLogService iAPLogService;
	
	private HttpServletResponse response;
	
	private static Logger log = Logger.getLogger(IAPPurchase.class); 
	
	private static String sandBox="https://sandbox.itunes.apple.com/verifyReceipt";
	
	private static String production="https://buy.itunes.apple.com/verifyReceipt";
	
	
	
    
   public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setiAPLogService(IAPLogService iAPLogService) {
		this.iAPLogService = iAPLogService;
	}

	public void iapPurchase()
    {
	  
		log.info("receiptToken -->" + CustomBase64.decode(this.receipt));
				
		log.info("before base 64 "+ CustomBase64.decode(this.receipt));
						
		String success = null;
		
		CreateJson receiptJson=new CreateJson();
		
		receiptJson.add("receipt-data",  CustomBase64.decode(this.receipt));
		
		String receipt=receiptJson.toString(true);
		
//		String receipt="{\"receipt-data\" : \""+ receiptBase + "\"}";
		
		log.info("receipt ->"+receipt);
				   
	    String responseJSON= ""; 
	        
	    try {
	    	   	
	     success=ConnectUtiltiy.send(receipt,Boolean.valueOf(this.sandbox)?sandBox:production);
	     	
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
			   
			   if(this.productId.equals(appProductId)&&appQuantity==Integer.valueOf(this.quantity))
			   {
				   // add diamond
				   
				   List <User> list=userProfileService.queryUserById(Long.valueOf(this.account));
				   
				   //add to iapLog
				   
				   List <Product> productList=productService.queryById(appProductId);
				   
				   int price=productList.get(0).getDiamond();
				   
				   int money=price*appQuantity;
				   
				   int diamond=appQuantity*productList.get(0).getUnit();
				   
				 
				   if(list.size()>0)
				   {
					   iAPLogService.addIAPLog(Integer.valueOf(CustomBase64.decode(this.account)), appProductId,
							   diamond, money, 1, Utility.getNowString());
					   
					   User user=list.get(0);
					   
					   user.setDiamond(user.getDiamond()+diamond);
					   user.setGold(user.getGold());
					   
					   userProfileService.updateGold(user);
					    
					   CreateJson prodcut=new CreateJson();
					   prodcut.add("status", 0);
					   prodcut.add("product_id", appProductId);
				   
					   responseJSON=prodcut.normalToString(true);
				   
					   response.setStatus(200);
					   
					   
				   
				   }
				   else
				   {
					   iAPLogService.addIAPLog(Integer.valueOf(CustomBase64.decode(this.account)), appProductId, appQuantity, money, -1, Utility.getNowString()); 
					      
					   ErrorResponse errorResponse = new ErrorResponse();
					   errorResponse.setErrorMessage("error useraccount");
					   errorResponse.setErrorAction("");
					   errorResponse.setErrorCode(ErrorCode.UserAuth_UserNotExist);
						
					   responseJSON=net.sf.json.JSONObject.fromObject(errorResponse).toString();
					   
					   response.setStatus(201);
					   
					  
				   }
			  
			   }
			   else
			   {
				   
				   iAPLogService.addIAPLog(Integer.valueOf(CustomBase64.decode(this.account)), appProductId, appQuantity, 0, -2, Utility.getNowString());
				   
				   CreateJson prodcut=new CreateJson();
				   prodcut.add("statue", -1);
				   prodcut.add("product_id", "no such productionId");
				   responseJSON=prodcut.toString(false);
				   
				   response.setStatus(300);
				   
				   
			   }
			   
		   }
		   else
		   {
			   iAPLogService.addIAPLog(Integer.valueOf(CustomBase64.decode(this.account)), this.productId, Integer.valueOf(this.quantity), 0, -3, Utility.getNowString());
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
			responseJSON=CustomBase64.encode(responseJSON);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
		  e.printStackTrace();
		}
	   	   
   }

    public void setAccount(String account) {
		this.account = account;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setSandbox(String sandbox) {
		this.sandbox = sandbox;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}


	public static void setProduction(String production) {
		IAPPurchase.production = production;
	}
	
	

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

}
