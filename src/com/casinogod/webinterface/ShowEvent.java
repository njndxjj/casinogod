package com.casinogod.webinterface;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.BossInstanceUserInfo;
import com.casinogod.pojo.BossUserInfo;
import com.casinogod.pojo.EventConfig;
import com.casinogod.service.BossBattleService;
import com.casinogod.service.BossInstanceUserService;
import com.casinogod.service.BossUserService;
import com.casinogod.service.EventService;
import com.casinogod.service.LogInRewardService;
import com.casinogod.service.UserDeviceService;
import com.casinogod.utility.CustomBase64;
import com.casinogod.utility.ErrorCode;
import com.casinogod.utility.PushNotificationThread;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionSupport;

public class ShowEvent extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	
	private String enable;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
		
	private EventService eventService;
	

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.resquest=request;
	}
	

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	private static Logger log = Logger.getLogger(ShowEvent.class); 

	public void showEvent()
	
	{
		
		String responseJSON  = "";
		if(CustomBase64.decode(this.enable)!=null)
		{
			//share raidboss 
		
		//response
		
		List <EventConfig> allInstanceUser=eventService.queryByEnable(Integer.valueOf(CustomBase64.decode(this.enable)));
		
		if(allInstanceUser.size()>0)
		{
			
			Map <String,Object> map=new HashMap<String, Object>();
			
			map.put("eventInfo", allInstanceUser);
			
			responseJSON += JSONObject.fromObject(map).toString();
			  
			response.setStatus(200);
		}
		else
		{
			 ErrorResponse errorResponse = new ErrorResponse();
			 errorResponse.setErrorMessage("event  fail!");
			 errorResponse.setErrorAction("");
			 errorResponse.setErrorCode(ErrorCode.Battle_shareRaidBoss);
				
			 responseJSON = JSONObject.fromObject(errorResponse).toString();
			 response.setStatus(401);
		}
		
		
		try {
			responseJSON=CustomBase64.encode(responseJSON);
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
	
	
	
}
