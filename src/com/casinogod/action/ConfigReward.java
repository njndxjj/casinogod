package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.LogInRewardConfig;
import com.casinogod.service.LogInRewardConfigService;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigReward extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private int day;
	
	private int reward;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private LogInRewardConfigService logInRewardConfigService;
	
	public void setDay(int day) {
		this.day = day;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setLogInRewardConfigService(
			LogInRewardConfigService logInRewardConfigService) {
		this.logInRewardConfigService = logInRewardConfigService;
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
		
		List <LogInRewardConfig> list=logInRewardConfigService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("rewardInfos", list);
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addRewardInfo()
	{
		boolean flag=false;
		
		flag=logInRewardConfigService.add(this.day, this.reward);
		
		if(flag)
			return queryAll();
		return ERROR;
		
	}
	
	public String update()
	{
		logInRewardConfigService.update(this.day, this.reward,this.id);
		return queryAll();
	}
	
	public String delete()
	{
		logInRewardConfigService.delete(this.id);
		return queryAll();
	}

}
