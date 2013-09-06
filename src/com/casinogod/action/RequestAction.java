package com.casinogod.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.FriendRequest;
import com.casinogod.service.RequestConfigService;
import com.casinogod.utility.Utility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RequestAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long userId;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private RequestConfigService requestConfigService;

	public void setUserId(long userId) {
		this.userId = userId;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
		
	public void setRequestConfigService(RequestConfigService requestConfigService) {
		this.requestConfigService = requestConfigService;
	}


	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public String sendRequest()
	{
		long owenId=Long.valueOf((String)ActionContext.getContext().getSession().get("account"));
		
		String dateTime=Utility.getNowString();
		
		requestConfigService.addFriendRequest(owenId, this.userId, dateTime,0);
			
		return SUCCESS;
	}	
	
	public String querryRequest()
	{
		long userId=Long.valueOf((String)ActionContext.getContext().getSession().get("account"));
		
		List <FriendRequest> requests=requestConfigService.queryUserId(userId);
		
		List <String> owenIds=new ArrayList<String>();
		
		if(requests.size()>0)
		{
			for(FriendRequest request :requests)
			{
				owenIds.add(String.valueOf(request.getOwenId()));
			}
			
			resquest.setAttribute("friends",owenIds);
			return SUCCESS;
		}
		
			
		return ERROR;
	}
	


	


}
