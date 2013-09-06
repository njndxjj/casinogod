package com.casinogod.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.BoardCastInfo;
import com.casinogod.pojo.Item;
import com.casinogod.service.BoardCastService;
import com.casinogod.service.ItemConfigService;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigBoardCast extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String title;
	
	private String content;
	
	private String startDate;
	
	private String endDate;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private BoardCastService boardCastService;

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		this.resquest=resquest;	
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setBoardCastService(BoardCastService boardCastService) {
		this.boardCastService = boardCastService;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <BoardCastInfo> list=boardCastService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("boardCastInfo", list);
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addBoardCastInfo()
	{
		boolean flag=false;
		
		flag=boardCastService.addBoardCastInfo(this.title, this.content, this.startDate, this.endDate);
	
		return flag?SUCCESS:ERROR;
		
	}
	
	public String queryBetween()
	{
		boolean flag=false;
		
		List <BoardCastInfo> list=boardCastService.queryBybetwend(this.startDate, this.endDate);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("boardCastInfo", list);
		}
		return flag? SUCCESS:ERROR;
	}

}
