package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.EventDAOImpl;
import com.casinogod.pojo.EventConfig;

public class EventService implements EventInterface {
	
	private EventConfig eventConfig;
	
	private EventDAOImpl eventDAO;
	
	public void setEventConfig(EventConfig eventConfig) {
		this.eventConfig = eventConfig;
	}

	public void setEventDAO(EventDAOImpl eventDAO) {
		this.eventDAO = eventDAO;
	}

	public List<EventConfig> queryall() {
		// TODO Auto-generated method stub
		return eventDAO.querAll();
	}

	public boolean addEvent(int eventType, String startTime, String endTime,
			String description, String title, String imageUrl, int frequency,
			String detailData,int enable) {
		// TODO Auto-generated method stub
		eventConfig.setDescription(description);
		eventConfig.setDetailData(detailData);
		eventConfig.setEndTime(endTime);
		eventConfig.setEventType(eventType);
		eventConfig.setFrequency(frequency);
		eventConfig.setImageUrl(imageUrl);
		eventConfig.setStartTime(startTime);
		eventConfig.setTitle(title);
		eventConfig.setEnable(enable);
		eventDAO.insertEvent(eventConfig);
		
		return true;
	}

	public List<EventConfig> queryById(int eventId) {
		// TODO Auto-generated method stub
		return eventDAO.queryById(eventId);
	}

	public boolean updateEventConfig(EventConfig eventConfig) {
		// TODO Auto-generated method stub
		return eventDAO.updateEvent(eventConfig);
	}

	public boolean deleteEventConfig(int eventId) {
		// TODO Auto-generated method stub
		return eventDAO.deleteEvent(eventId);
	}

	public List<EventConfig> queryByType(int eventType) {
		// TODO Auto-generated method stub
		return eventDAO.queryByType(eventType);
	}

	public List<EventConfig> queryByEnable(int enable) {
		// TODO Auto-generated method stub
		return eventDAO.queryByEnable(enable);
	}

}
