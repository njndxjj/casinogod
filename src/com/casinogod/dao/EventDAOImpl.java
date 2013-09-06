package com.casinogod.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.EventConfig;

public class EventDAOImpl extends SqlMapClientDaoSupport implements EventDAO {

	public List<EventConfig> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("eventConfig.findAllEvent");
	}

	public void insertEvent(EventConfig eventConfig) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("eventConfig.insertEvent", eventConfig);
	}

	public List<EventConfig> queryById(int eventId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("eventConfig.findEventById",eventId);
	}

	public boolean updateEvent(EventConfig eventConfig) {
		// TODO Auto-generated method stub
		int countUpdate;
		countUpdate=this.getSqlMapClientTemplate().update("eventConfig.updateEvent", eventConfig);
		return countUpdate>0?true:false;
	}

	public boolean deleteEvent(int eventId) {
		// TODO Auto-generated method stub
		int countDelete;
		countDelete=this.getSqlMapClientTemplate().delete("eventConfig.deleteEvent", eventId);
		return countDelete>0?true:false;
	}

	public List<EventConfig> queryByType(int eventType) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("eventConfig.findEventByType",eventType);
	}

	public List<EventConfig> queryByEnable(int enable) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("eventConfig.findEventByEnable",enable);	
		
	}

}
