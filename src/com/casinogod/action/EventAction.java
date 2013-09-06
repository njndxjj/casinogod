package com.casinogod.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.casinogod.pojo.EventConfig;
import com.casinogod.pojo.Item;
import com.casinogod.service.EventService;
import com.casinogod.service.ItemConfigService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EventAction extends ActionSupport implements ServletResponseAware,ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int eventId;
	
	private int eventType;
	
	private String startTime;
	
	private String endTime;
	
	private String description;
	
	private String title;
	
	private String imageUrl;
	
	private int frequency;
	
	private String detailData;
	
	private int enable;
	
	private HttpServletResponse response;
	
	private HttpServletRequest resquest;
	
	private EventService eventService;

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}

   
	public void setEnable(int enable) {
		this.enable = enable;
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
		
		List <EventConfig> list=eventService.queryall();
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("eventInfos", list);
		}
		
		return flag? SUCCESS:ERROR;
	}	
	
	public String addEventInfo()
	{
		boolean flag=false;
		
		flag=eventService.addEvent(this.eventType, this.startTime, this.endTime, this.description,
				this.title, this.imageUrl, this.frequency, this.detailData,this.enable);
	
		return flag?SUCCESS:ERROR;
		
	}
	
	public String findEventById() 
	{
		boolean flag=false;
		
		List <EventConfig> list=eventService.queryById(this.eventId);
		
		if(list.size()>0)
		{
			flag=true;
			resquest.setAttribute("eventId", list.get(0).getEventId());
			resquest.setAttribute("eventType", list.get(0).getEventType());				
			resquest.setAttribute("startTime", list.get(0).getStartTime());
			resquest.setAttribute("endTime", list.get(0).getEndTime());
			resquest.setAttribute("description", list.get(0).getDescription());
			resquest.setAttribute("title", list.get(0).getTitle());
			resquest.setAttribute("imageUrl", list.get(0).getImageUrl());
			resquest.setAttribute("frequency", list.get(0).getFrequency());
			resquest.setAttribute("detailData", list.get(0).getDetailData());
			resquest.setAttribute("enable", list.get(0).getEnable());
		}
		
		return flag? SUCCESS:ERROR;
	}
	
	public String updateEvent() 
	{
		boolean flag=false;
		
		EventConfig event=new EventConfig();
		
		event.setEventId(this.eventId);
		
		event.setEventType(this.eventType);
		
		event.setDescription(this.description);
		
		event.setDetailData(this.detailData);
		
		event.setEndTime(this.endTime);
		
		event.setStartTime(this.startTime);
		
		event.setTitle(this.title);
		
		event.setImageUrl(this.imageUrl);
		
		event.setFrequency(this.frequency);
		
		event.setEnable(this.enable);
		
		flag=eventService.updateEventConfig(event);
		
		return flag? SUCCESS:ERROR;
	}
	
	public String deleteById() 
	{
		boolean flag=false;
		
		flag=eventService.deleteEventConfig(eventId);
		
		return flag? SUCCESS:ERROR;
	}
}
