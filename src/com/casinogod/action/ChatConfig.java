package com.casinogod.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.User;
import com.casinogod.service.UserProfile;
import com.opensymphony.xwork2.ActionSupport;

public class ChatConfig  extends ActionSupport implements ServletResponseAware,ServletRequestAware{
	
    
	private HttpServletResponse response;
	
	private HttpServletRequest request;
	
	private UserProfile userProfileService;
	
	private String account;
	
	private static Logger log = Logger.getLogger(ChatConfig.class);
	
  // private HttpServletRequest request=ServletActionContext.getRequest();
   
  
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void setUserProfileService(UserProfile userProfileService) {
		this.userProfileService = userProfileService;
	}
	

	public void setAccount(String account) {
		this.account = account;
	}

	public void chatSetting()  {
		
		List <User> userList=new ArrayList<User>();
		
	    String useraccount=(String)ServletActionContext.getRequest().getSession().getAttribute("account");
		
	    if(useraccount!=null)
	    
	    	userList=userProfileService.queryUserById(Long.valueOf(useraccount));
	    
	    else
	    	
	    	userList=userProfileService.queryUserById(Long.valueOf(this.account));
	  
		if(userList.size()>0)
			
		{
			PrintWriter pw = null;
			try {
				pw = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			request.getSession().setAttribute("user", userList.get(0));
			pw.write("<html><title></title><head><script type='text/javascript'> window.open('chat-room.jsp','chater','status=0,scrollbars=0,menubar=0,location=0,width=600,height=400');</script></head><body></body></html>");
			pw.flush();
			pw.close();
			return;  
			
		}
		 
	  }
    
   
                   
    }
    

