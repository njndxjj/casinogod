package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.EventConfig;
import com.casinogod.pojo.Item;


public interface EventInterface {
	
	public List<EventConfig> queryall();
	
	public boolean addEvent(int eventType,String startTime, String endTime,String description,
			String title,String imageUrl,int frequency,String detailData,int enable);
	
	public List<EventConfig> queryById(int eventId);
	
	public List<EventConfig> queryByType(int eventType);
	
	public List<EventConfig> queryByEnable(int enable);
	
	public boolean  updateEventConfig(EventConfig eventConfig);
	
	public boolean deleteEventConfig(int eventId);
	
}
