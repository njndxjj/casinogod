package com.casinogod.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.Item;
import com.casinogod.pojo.RankType;
import com.casinogod.service.ItemConfigService;
import com.casinogod.service.RankTypeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigRankType extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int typeId;
	
	private String rankName;
	
	private String rankDescription;
	
	private String startTime;
	
	private String endTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private RankTypeService rankTypeService;

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}
	
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public void setRankDescription(String rankDescription) {
		this.rankDescription = rankDescription;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setRankTypeService(RankTypeService rankTypeService) {
		this.rankTypeService = rankTypeService;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
		
		List <RankType> list=rankTypeService.queryAll();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("RankTypeInfos", list);
			Map<String, Object> attibutes = ActionContext.getContext().getSession();

		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addRank()
	{
		boolean flag=false;
		
		flag=rankTypeService.addRankType(this.rankName, this.rankDescription, this.startTime, this.endTime);
	
		return flag?SUCCESS:ERROR;
		
	}
	
	public String findById() 
	{
		boolean flag=false;
		
		List <RankType> list=rankTypeService.queryById(this.typeId);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("typeId", list.get(0).getTypeId());
			resquest.setAttribute("rankName", list.get(0).getRankName());				
			resquest.setAttribute("rankDescription", list.get(0).getRankDescription());
			resquest.setAttribute("startTime", list.get(0).getStartTime());
			resquest.setAttribute("endTime", list.get(0).getEndTime());
		}
		
		return flag? SUCCESS:ERROR;
	}
	
	public String updateRank() 
	{
		boolean flag=false;
		
		RankType rankType=new RankType();
		
		rankType.setTypeId(this.typeId);
		rankType.setRankName(this.rankName);
		rankType.setStartTime(this.startTime);
		rankType.setRankDescription(this.rankDescription);
		rankType.setEndTime(this.endTime);
		
		flag=rankTypeService.updateRankType(rankType);
		
		return flag? SUCCESS:ERROR;
	}
////	
	public String deleteRankById() 
	{
		boolean flag=false;
		
		flag=rankTypeService.deleteRankType(this.typeId);
		
		return flag? SUCCESS:ERROR;
	}
}
