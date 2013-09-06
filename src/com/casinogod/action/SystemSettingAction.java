package com.casinogod.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Configuration;
import com.casinogod.pojo.Item;
import com.casinogod.service.ConfigurationService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SystemSettingAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int winTimes;

	private int rankSize;
	
	private int id;
	
	private int bossRewardNum;
	
	private int invitedReward;
	
	private int invitedItem;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private ConfigurationService configurationService;

    
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

   
	public void setWinTimes(int winTimes) {
		this.winTimes = winTimes;
	}

	public void setRankSize(int rankSize) {
		this.rankSize = rankSize;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public void setBossRewardNum(int bossRewardNum) {
		this.bossRewardNum = bossRewardNum;
	}
	
	
	public void setInvitedReward(int invitedReward) {
		this.invitedReward = invitedReward;
	}

	public void setInvitedItem(int invitedItem) {
		this.invitedItem = invitedItem;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <Configuration> list=configurationService.querAll();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("ConfigurationInfos", list);
			Map<String, Object> attibutes = ActionContext.getContext().getSession();
			
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addConfigInfo()
	{
		boolean flag=false;
		
		configurationService.insertConfiguration(this.winTimes, this.rankSize,
				this.bossRewardNum,this.invitedReward,this.invitedItem);
	
		return SUCCESS;
		
	}
	
	public String findById() 
	{
		boolean flag=false;
		
		List <Configuration> list=configurationService.queryById(this.id);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("id", list.get(0).getId());
			resquest.setAttribute("winTimes", list.get(0).getWinTimes());				
			resquest.setAttribute("rankSize", list.get(0).getRankSize());
			resquest.setAttribute("bossRewardNum", list.get(0).getBossRewardNum());
			resquest.setAttribute("invitedReward", list.get(0).getInvitedReward());
			resquest.setAttribute("invitedItem", list.get(0).getInvitedItem());
		}
		
		return flag? SUCCESS:ERROR;
	}
	
	public String updateConfig() 
	{
		boolean flag=false;
	
		
		flag=configurationService.updateConfiguration(this.winTimes, this.rankSize, 
				this.id,this.bossRewardNum,this.invitedReward,this.invitedItem);
		
		return flag? SUCCESS:ERROR;
	}

}
