package com.casinogod.webinterface;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.UserDevice;
import com.casinogod.service.UserDeviceService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterPN extends ActionSupport implements ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userAccount;
	
	private String deviceToken;
	
	private String notes;
	
	
	private HttpServletResponse response;
	
	
	private UserDeviceService userDeviceService;

   
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public void setUserDeviceService(UserDeviceService userDeviceService) {
		this.userDeviceService = userDeviceService;
	}



	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	public void registerDevice()
	{
		boolean flag=false;
		
		if(CustomBase64.decode(this.deviceToken)!=null&&Long.valueOf(CustomBase64.decode(this.userAccount))>0)
			flag=userDeviceService.addDevice(Long.valueOf(CustomBase64.decode(this.userAccount)), CustomBase64.decode(this.deviceToken),
					Utility.getNowString(), this.notes);
		
		String responseJSON= "";    
		   
	    if(flag)  
	    {  
	    	System.out.println("register device success: "+this.deviceToken);     	
			HashMap deviceTokeMap=new HashMap();
			deviceTokeMap.put("deviceToke", this.deviceToken);
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
	
	
	
}
