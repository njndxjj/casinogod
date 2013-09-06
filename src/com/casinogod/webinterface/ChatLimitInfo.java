package com.casinogod.webinterface;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.ChatLimit;
import com.casinogod.pojo.Item;
import com.casinogod.service.ChatLimitService;
import com.casinogod.service.ItemConfigService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChatLimitInfo extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String account;
		
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private ChatLimitService chatLimitService;

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}


	public void setChatLimitService(ChatLimitService chatLimitService) {
		this.chatLimitService = chatLimitService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	public void setAccount(String account) {
		this.account = account;
	}

	public void queryAll()
	{

		List <ChatLimit> list=chatLimitService.queryAll();
		
		String responseJSON= "";    
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("ChatLimit", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	       
	       try {
			responseJSON=CustomBase64.encode(responseJSON);
	       } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	       }
	    	
		   response.setStatus(200);	
	    
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any ChatLimit Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.lottery_Information);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			
			try {
				responseJSON=CustomBase64.encode(responseJSON);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setStatus(401);
		}
			
		try {
				
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseJSON);
				
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}
	
	public void queryById()
	{

		List <ChatLimit> list=chatLimitService.queryUserById(Integer.valueOf(CustomBase64.decode(this.account)));
		
		String responseJSON= "";    
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("ChatLimit", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	       
	       try {
			responseJSON=CustomBase64.encode(responseJSON);
	       } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	       }
	    	
		   response.setStatus(200);	
	    
	    }
	    
		else
		{
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorMessage("Cannot find any ChatLimit Infromation");
			errorResponse.setErrorAction("");
			errorResponse.setErrorCode(ErrorCode.UserAuth_ChatLimit);
				
			responseJSON = JSONObject.fromObject(errorResponse).toString();
			try {
				responseJSON=CustomBase64.encode(responseJSON);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setStatus(501);
		}
			
		try {
				
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
