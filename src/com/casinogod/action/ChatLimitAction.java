package com.casinogod.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.ChatLimit;
import com.casinogod.pojo.FriendRequest;
import com.casinogod.service.ChatLimitService;
import com.casinogod.service.RequestConfigService;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChatLimitAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String account;
	
	private String startTime;
	
	private String endTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private ChatLimitService chatLimitService;

	


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
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
	
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setChatLimitService(ChatLimitService chatLimitService) {
		this.chatLimitService = chatLimitService;
	}

	public String querryAll()
	{
		boolean flag=false;
		List <ChatLimit> lists=chatLimitService.queryAll();
		
		if(lists.size()>0)
		{
			resquest.setAttribute("chatLimitList", lists);
			flag=true;
		}
			
		return  flag?SUCCESS:ERROR;
	}	
	
	public String querryByUserId()
	{
		boolean flag=false;
		
		List <ChatLimit> lists=chatLimitService.queryUserById(Integer.valueOf(account));
		
		
		if(lists.size()>0)
		{
			resquest.setAttribute("account", lists.get(0).getUserId());
			resquest.setAttribute("startTime", lists.get(0).getStartTime());
			resquest.setAttribute("endTime", lists.get(0).getEndTime());
			flag=true;
			
		}
		
			
		return flag?SUCCESS:ERROR;
	}
	
	public String update()
	{
		boolean flag=false;
		
		flag=chatLimitService.update(this.startTime, this.endTime,Integer.valueOf( this.account));
		
			
		return flag?SUCCESS:ERROR;
	}
	
	
	public String delete()
	{
		boolean flag=false;
		
		flag=chatLimitService.delete(Integer.valueOf(this.account));
		
			
		return flag?SUCCESS:ERROR;
	}
	
	public String add()
	{
		boolean flag=false;
		
		flag=chatLimitService.addChatLimit(Integer.valueOf(this.account), this. startTime, this.endTime);
		
		return flag?SUCCESS:ERROR;
	}
	
	


	


}
