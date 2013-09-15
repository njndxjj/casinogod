package com.casinogod.webinterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.UserDevice;
import com.casinogod.service.UserDeviceService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterPN extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	
	private UserDeviceService userDeviceService;

   

	public void setUserDeviceService(UserDeviceService userDeviceService) {
		this.userDeviceService = userDeviceService;
	}



	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	
	
	public void registerDevice()
	{
		boolean flag=false;
		
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
	    
	    String account=Utility.splitString(decode, "account");
	    String deviceToken=Utility.splitString(decode, "deviceToken");
	    String notes=Utility.splitString(decode, "notes");
		
		if(deviceToken!=null&&Long.valueOf(account)>0)
			flag=userDeviceService.addDevice(Long.valueOf(account), deviceToken,
					Utility.getNowString(), notes);
		
		String responseJSON= "";    
		   
	    if(flag)  
	    {  
	    	System.out.println("register device success: "+deviceToken);     	
			HashMap deviceTokeMap=new HashMap();
			deviceTokeMap.put("deviceToke", deviceToken);
			responseJSON=Utility.getJSONFromHash(deviceTokeMap);
			response.setStatus(200);	
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("register device:  error");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.register_Device);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			response.setStatus(401);
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

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.resquest=request;
	}
	
	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	
	
}
