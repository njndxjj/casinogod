package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.Task;

public class TaskDAOImpl extends SqlMapClientDaoSupport implements TaskDAO {

	public List<Task> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("task.findAll");
	}

	public void insertTask(Task task) {
		// TODO Auto-generated method stub		
		this.getSqlMapClientTemplate().insert("task.insertTask", task);

	}

	public List<Task> queryByType(int taskType) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("task.findByType", taskType);
	}

	public boolean updateType(Task task) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("task.updateTask", task);
		return updateCount>0?true:false;
	}

	public boolean deleteType(int taskType, int taskRate) {
		// TODO Auto-generated method stub
		Map <Object,Object> map=new HashMap<Object, Object>();
		int deleteCount;
		map.put("taskType", taskType);
		map.put("taskRate", taskRate);
		deleteCount=this.getSqlMapClientTemplate().delete("task.deleteTask", map);
		return deleteCount>0?true:false;
	}

	public List<Task> queryByTypeAndRate(int taskType, int taskRate) {
		// TODO Auto-generated method stub
		Map <Object,Object> map=new HashMap<Object, Object>();
		map.put("taskType", taskType);
		map.put("taskRate", taskRate);
		return this.getSqlMapClientTemplate().queryForList("task.findByTypeAndRate", map);
	}

	public List<Task> querAllStatus() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("task.findAllStatus");
	}

	public List<Task> queryById(int taskId) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("task.findById", taskId);
	}

}
