package com.casinogod.webinterface;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.ChatLimit;
import com.casinogod.pojo.ChatRoom;
import com.casinogod.service.ChatLimitService;
import com.casinogod.service.ChatRoomService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class ChatRoomInfo extends ActionSupport implements ServletResponseAware,ServletRequestAware {

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
	
	

	public void setContent(String content) {
		this.content = content;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setChatRoomService(ChatRoomService chatRoomService) {
		this.chatRoomService = chatRoomService;
	}

	public void queryAll()
	{

		List <ChatRoom> list=chatRoomService.queryAll();
		
		String responseJSON= "";    
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("ChatRoom", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	       
	       try {
			responseJSON=CustomBase64.encode(responseJSON);
	       } catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	public void add()
	
	{
		
		boolean flag=false;
	
		boolean chat=true;
		
		List <ChatLimit> chatList=chatLimitService.queryUserById(Integer.valueOf(CustomBase64.decode(this.account)));
		
		if(chatList.size()>0)
		{
			ChatLimit chatLimit=chatList.get(0);
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			try {
				
				Date startTime=format.parse(chatLimit.getStartTime());
				Date endTime=format.parse(chatLimit.getEndTime());
				Date curretTime=format.parse(Utility.getNowString());
				
				if(curretTime.compareTo(startTime)>=0&&curretTime.compareTo(endTime)<=0)
					chat=false;
				else
				{
					flag=chatRoomService.addChatRoom(Integer.valueOf(CustomBase64.decode(this.account)), CustomBase64.decode(this.content), Utility.getNowString());
				}
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			//chat 
			flag=chatRoomService.addChatRoom(Integer.valueOf(CustomBase64.decode(this.account)),CustomBase64.decode(this.content), Utility.getNowString());
		}
		
		String responseJSON= "";    
	
	 if(flag&&chat)
		
	 {
		 
	  List <ChatRoom> list=chatRoomService.queryAllTime(CustomBase64.decode(this.updateTime));
		   
	    if(list.size()>0)  
	    {  	    	
	    
	       HashMap< String, Object> map = new HashMap<String, Object>();
	    	
	       map.put("ChatRoom", list);
	       
	       responseJSON +=JSONObject.fromObject(map).toString();
	       
	       try {
			responseJSON=CustomBase64.encode(responseJSON);
	       } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	       }
	    	
		   response.setStatus(200);	
	    
	    }
	 }
	 else if(!chat)
	 {
			
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setErrorMessage("Cannot chat in this time");
		 errorResponse.setErrorAction("");
		 errorResponse.setErrorCode(ErrorCode.UserAuth_ChatLimit);
				
		 responseJSON = JSONObject.fromObject(errorResponse).toString();
		 
		 try {
				responseJSON=CustomBase64.encode(responseJSON);
			 } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
		 
		 response.setStatus(401);
	}
	else
	 {
			
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setErrorMessage("error insert");
		 errorResponse.setErrorAction("");
		 errorResponse.setErrorCode(ErrorCode.db_insert);
				
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
	
}
