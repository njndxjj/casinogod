package com.casinogod.action;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.RoomInfo;
import com.casinogod.service.RoomProfileService;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigRoomInfo extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239037834014437137L;
	
	private int id;
	
	private int gameType;
	
	private String roomLevel;
	
	private int levelScore;
	
	private int minHand;

	private int maxHand;
	
	private String otherNotes;
	
	private String beginTime;
	
	private String endTime;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private RoomProfileService roomProfileService;
	

	private static Logger log = Logger.getLogger(ConfigRoomInfo.class); 

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public void setRoomLevel(String roomLevel) {
		this.roomLevel = roomLevel;
	}

	public void setLevelScore(int levelScore) {
		this.levelScore = levelScore;
	}

	public void setMinHand(int minHand) {
		this.minHand = minHand;
	}

	public void setMaxHand(int maxHand) {
		this.maxHand = maxHand;
	}

	public void setOtherNotes(String otherNotes) {
		this.otherNotes = otherNotes;
	}
	
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

	public void setRoomProfileService(RoomProfileService roomProfileService) {
		this.roomProfileService = roomProfileService;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String queryAll()
	{
		boolean flag=false;
		
		List <RoomInfo> list=roomProfileService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("roomInfos", list);
		}
		
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addRoomInfo()
	{
		boolean flag=false;
		
		if(this.beginTime.equals("")||this.endTime.equals(""))
		{
			this.beginTime=this.endTime="1000-01-01 01:01:01";
		}
		
		flag=roomProfileService.addRoomProfile(this.gameType, this.roomLevel,
				this.levelScore,this.minHand, this.maxHand, this.beginTime,this.endTime,this.otherNotes);
		
		
		return flag?SUCCESS:ERROR;
		
		
	}
	
	public String updateRoom()
	
	{
		boolean flag=false;
		
		RoomInfo roomInfo=new RoomInfo();
		
		roomInfo.setId(this.id);
		roomInfo.setLevelScore(this.levelScore);
	    String startTime=this.beginTime;
		startTime=startTime.replace(String.valueOf(startTime.charAt(10)), "\t");
		roomInfo.setBeginTime(startTime);
		
		String endTime=this.endTime;
		endTime=endTime.replace(String.valueOf(endTime.charAt(10)), "\t");
		
		
		roomInfo.setEndTime(endTime);
		roomInfo.setGameType(this.gameType);
		roomInfo.setMinHand(this.minHand);
		roomInfo.setMaxHand(this.maxHand);
		roomInfo.setOtherNotes(this.otherNotes);
		roomInfo.setRoomLevel(this.roomLevel);
		
		flag=roomProfileService.update(roomInfo);
		
		
		return flag?SUCCESS:ERROR;
	}
	
	
	public String deleteRoom()
	
	{
		boolean flag=false;
		
		
		flag=roomProfileService.delete(id);
		
		
		return flag?SUCCESS:ERROR;
	}
	
	

	public void setServletRequest(HttpServletRequest resquest) {
		// TODO Auto-generated method stub
		
		this.resquest=resquest;
		
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		this.response=response;
	}

}
