package com.casinogod.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.ChatLimit;
import com.casinogod.pojo.ChatRoom;
import com.casinogod.pojo.FriendRequest;
import com.casinogod.service.ChatLimitService;
import com.casinogod.service.ChatRoomService;
import com.casinogod.service.RequestConfigService;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChatRoomAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String account;
	
	private String content;
	
	private String updateTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private ChatLimitService chatLimitService;
	
	private ChatRoomService chatRoomService;

	


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

	public void setContent(String content) {
		this.content = content;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setChatRoomService(ChatRoomService chatRoomService) {
		this.chatRoomService = chatRoomService;
	}

	public void setChatLimitService(ChatLimitService chatLimitService) {
		this.chatLimitService = chatLimitService;
	}

	public String querryAllTime()
	{
		boolean flag=false;
		List <ChatRoom> lists=chatRoomService.queryAllTime(this.updateTime);
		
		if(lists.size()>0)
		{
			resquest.setAttribute("chatRoomList", lists);
			flag=true;
		}
			
		return  flag?SUCCESS:ERROR;
	}	
	
	public String querryAll()
	{
		boolean flag=false;
		List <ChatRoom> lists=chatRoomService.queryAll();
		
		if(lists.size()>0)
		{
			resquest.setAttribute("chatRoomList", lists);
			flag=true;
		}
			
		return  flag?SUCCESS:ERROR;
	}	
	
	public String querryByUserId()
	{
		boolean flag=false;
		
		List <ChatRoom> lists=chatRoomService.queryUserById(Integer.valueOf(account));
		
		
		if(lists.size()>0)
		{
			resquest.setAttribute("chatRoomList", lists);
			flag=true;
			
		}
		
			
		return flag?SUCCESS:ERROR;
	}

	
	public String add()
	{
		boolean flag=false;
		
		List <ChatLimit> chatList=chatLimitService.queryUserById(Integer.valueOf(this.account));
		
		if(chatList.size()>0)
		{
			ChatLimit chatLimit=chatList.get(0);
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Date startTime=format.parse(chatLimit.getStartTime());
				Date endTime=format.parse(chatLimit.getEndTime());
				Date curretTime=format.parse(Utility.getNowString());
				
				if(curretTime.compareTo(startTime)>=0&&curretTime.compareTo(endTime)<=0)
					flag=false;
				else
				{
					flag=chatRoomService.addChatRoom(Integer.valueOf(this.account), this.content, Utility.getNowString());
				}
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			//chat 
			flag=chatRoomService.addChatRoom(Integer.valueOf(this.account), this.content, Utility.getNowString());
		}
		
		return flag?SUCCESS:ERROR;
	}
	
	


	


}
