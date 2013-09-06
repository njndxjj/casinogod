package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.ItemHistory;
import com.casinogod.pojo.ItemUser;
import com.casinogod.service.ItemHistoryConfigService;
import com.casinogod.service.ItemUserConfigService;
import com.opensymphony.xwork2.ActionSupport;

public class ItemAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long userId;
	
	private HttpServletResponse response;
	
	private HttpServletRequest request;
	
	private ItemHistoryConfigService itemHistoryConfigService;
	
	private ItemUserConfigService itemUserConfigService;
	
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
		
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		this.request=request;
		
	}
	
	public void setItemHistoryConfigService(
			ItemHistoryConfigService itemHistoryConfigService) {
		this.itemHistoryConfigService = itemHistoryConfigService;
	}
	public void setItemUserConfigService(ItemUserConfigService itemUserConfigService) {
		this.itemUserConfigService = itemUserConfigService;
	}
	public String queryItemUser()
	{
		boolean flag=false;
		
		List <ItemUser> lists=itemUserConfigService.queryByUserId(this.userId);
		
		if(lists.size()>0)
		{
			 flag=true;
			 request.setAttribute("useItems", lists);
		}
	
		return flag?SUCCESS:ERROR;
	}
	
	public String queryItemHistory()
	{
		boolean flag=false;
		
		List <ItemHistory> lists=itemHistoryConfigService.queryAllUser(this.userId);
		
		if(lists.size()>0)
		{
			 flag=true;
			 request.setAttribute("itemHistorys", lists);
		}
	
		return flag?SUCCESS:ERROR;

	}

}
