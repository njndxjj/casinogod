package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.BossConfig;
import com.casinogod.service.BossConfigService;
import com.opensymphony.xwork2.ActionSupport;

public class BossInfoAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bossId;
		
	private int maxHP;
	
	private int time;
	
	private String otherNotes;
	
	private int bossType;
	
	private int userSize;
	
	private String bossName;
	
	private String bossImage;
	
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private BossConfigService bossConfigService;

	

	public void setBossId(String bossId) {
		this.bossId = bossId;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	public void setBossConfigService(BossConfigService bossConfigService) {
		this.bossConfigService = bossConfigService;
	}

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void setOtherNotes(String otherNotes) {
		this.otherNotes = otherNotes;
	}

	public void setBossType(int bossType) {
		this.bossType = bossType;
	}
	
	public void setUserSize(int userSize) {
		this.userSize = userSize;
	}
	

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public void setBossImage(String bossImage) {
		this.bossImage = bossImage;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <BossConfig> list=bossConfigService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("bossConfig", list);
//			Map<String, Object> attibutes = ActionContext.getContext().getSession();
			
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addBossConfig()
	{
		boolean flag=false;
		
		flag=bossConfigService.addBossConfig(this.maxHP,this.time,this.otherNotes,
				this.bossType,this.userSize,this.bossName,this.bossImage);
	
		return flag?SUCCESS:ERROR;
		
	}
	
	public String findConfigById() 
	{
		boolean flag=false;
		
		List <BossConfig> list=bossConfigService.queryById(Integer.valueOf(this.bossId));
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("bossId", list.get(0).getBossId());
			resquest.setAttribute("maxHP", list.get(0).getMaxHP());				
			
		}
		
		return flag? SUCCESS:ERROR;
	}
	
	public String updateBossInfo() 
	{
		boolean flag=false;
		
	    BossConfig bossConfig=new BossConfig();
		
	    bossConfig.setBossId(Integer.valueOf(this.bossId));
	    bossConfig.setMaxHP(this.maxHP);
	    bossConfig.setTime(this.time);
	    bossConfig.setBossType(this.bossType);
	    bossConfig.setOtherNotes(this.otherNotes);
	    bossConfig.setUserSize(this.userSize);
	    bossConfig.setBossName(this.bossName);
	    bossConfig.setBossImage(this.bossImage);
	   
		
		
		flag=bossConfigService.updateBossConfig(bossConfig);
		
		return flag? SUCCESS:ERROR;
	}
////	
	public String deleteBossById() 
	{
		boolean flag=false;
		
		flag=bossConfigService.deleteBossConfig(Integer.valueOf(this.bossId));
		
		return flag? SUCCESS:ERROR;
	}
}
