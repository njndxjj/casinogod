package com.casinogod.webinterface;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.casinogod.pojo.Product;
import com.casinogod.pojo.SimpleUser;
import com.casinogod.pojo.User;
import com.casinogod.service.IAPLogService;
import com.casinogod.service.ProductService;
import com.casinogod.service.UserLogIn;
import com.casinogod.service.UserProfileService;
import com.casinogod.utility.ConnectUtiltiy;
import com.casinogod.utility.CreateJson;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class IAPPurchase extends ActionSupport implements ServletResponseAware,ServletRequestAware {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
    
	private UserProfileService userProfileService;
	
	private ProductService productService;
	
	private IAPLogService iAPLogService;
	
	private  UserLogIn userLogInService;
	
	private HttpServletResponse response;
	
	private static Logger log = Logger.getLogger(IAPPurchase.class); 
	
	private static String sandBox="https://sandbox.itunes.apple.com/verifyReceipt";
	
	private static String production="https://buy.itunes.apple.com/verifyReceipt";
	
	
	private HttpServletRequest resquest;
    
   public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setiAPLogService(IAPLogService iAPLogService) {
		this.iAPLogService = iAPLogService;
	}
	
	

	public void setUserLogInService(UserLogIn userLogInService) {
		this.userLogInService = userLogInService;
	}

	public void iapPurchase()
    {
		
		String postdata="";
		String decode="";
	    
	    try {
		
	    	postdata=Utility.postdata(resquest);
	    	decode=CustomBase64.decode(postdata);
	    	System.out.println("addLotteryInfo-->"+postdata);
	    	System.out.println("addLotteryInfo--->"+CustomBase64.decode(postdata));
		
	    } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    String receiptde=Utility.splitString(decode, "receipt");
	    String sandbox=Utility.splitString(decode, "sandbox");
	    String account=Utility.splitString(decode, "account");
	    String productId=Utility.splitString(decode, "productId");
	    String quantity=Utility.splitString(decode, "quantity");
	  
		log.info("receiptToken -->" + receiptde);
				
						
		String success = null;
		
		CreateJson receiptJson=new CreateJson();
		
		receiptJson.add("receipt-data",  receiptde);
		
		String receipt=receiptJson.toString(true);
		
///		String receipt="{\"receipt-data\" : \""+ receiptBase + "\"}";
		
		log.info("receipt ->"+receipt);
				   
	    String responseJSON= ""; 
	        
	    try {
	    	   	
	     success=ConnectUtiltiy.send(receipt,Boolean.valueOf(sandbox)?sandBox:production);
	     	
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
			   
			   if(productId.equals(appProductId)&&appQuantity==Integer.valueOf(quantity))
			   {
				   // add diamond
				   
				   List <User> list=userProfileService.queryUserById(Long.valueOf(account));
				   
				   //add to iapLog
				   
				   List <Product> productList=productService.queryById(appProductId);
				   
				   int price=productList.get(0).getDiamond();
				   
				   int money=price*appQuantity;
				   
				   int diamond=appQuantity*productList.get(0).getUnit();
				   
				 
				   if(list.size()>0)
				   {
					   iAPLogService.addIAPLog(Integer.valueOf(account), appProductId,
							   diamond, money, 1, Utility.getNowString());
					   
					   User user=list.get(0);
					   
					   user.setDiamond(user.getDiamond()+diamond);
					   user.setGold(user.getGold());
					   
					   userProfileService.updateGold(user);
					   
					   user=userProfileService.queryUserById(Long.valueOf(account)).get(0);
					   
					   SimpleUser simpleUser=new SimpleUser();
				
						String snsId=userLogInService.getAccount(user.getUserId()).getSnsId();
						simpleUser.setGold(user.getGold());
						simpleUser.setExp(user.getExp());
						simpleUser.setGender(user.getGender());
						simpleUser.setImage(user.getImage());
						simpleUser.setLevel(user.getLevel());
						simpleUser.setNickName(user.getNickName());
						simpleUser.setUserId(user.getUserId());
	//				   CreateJson prodcut=new CreateJson();
	//				   prodcut.add("status", 0);
	//				   prodcut.add("product_id", appProductId);
				   
					   responseJSON=net.sf.json.JSONObject.fromObject(simpleUser).toString();
				   
					   response.setStatus(200);
					   
					   
				   
				   }
				   else
				   {
					   iAPLogService.addIAPLog(Integer.valueOf(account), appProductId, appQuantity, money, -1, Utility.getNowString()); 
					      
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
				   
				   iAPLogService.addIAPLog(Integer.valueOf(account), appProductId, appQuantity, 0, -2, Utility.getNowString());
				   
				   CreateJson prodcut=new CreateJson();
				   prodcut.add("statue", -1);
				   prodcut.add("product_id", "no such productionId");
				   responseJSON=prodcut.toString(false);
				   
				   response.setStatus(300);
				   
				   
			   }
			   
		   }
		   else
		   {
			   iAPLogService.addIAPLog(Integer.valueOf(account), productId, Integer.valueOf(quantity), 0, -3, Utility.getNowString());
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

  
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}



	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.resquest=request;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	
	
	

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

}
