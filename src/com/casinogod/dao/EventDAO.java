package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.EventConfig;

public interface EventDAO {
   
	public List <EventConfig> querAll();
	
	public void insertEvent(EventConfig eventConfig);
	
	public List <EventConfig> queryById(int eventId);
	
	public List <EventConfig> queryByType(int eventType);
	
	public List <EventConfig> queryByEnable(int enable);
	
	public boolean updateEvent(EventConfig eventConfig);
	
	public boolean deleteEvent(int eventId);
	
}
