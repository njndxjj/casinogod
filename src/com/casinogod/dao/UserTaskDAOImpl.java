package com.casinogod.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.casinogod.pojo.Task;
import com.casinogod.pojo.UserTask;

public class UserTaskDAOImpl extends SqlMapClientDaoSupport implements UserTaskDAO {

	public List<UserTask> querAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userTask.findAll");
	}

	public void insertUserTask(UserTask userTask) {
		// TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert("userTask.insertUserTask", userTask);
	}

	public List<UserTask> queryById(int id) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("userTask.findById", id);
	}

	public boolean updateType(UserTask userTask) {
		// TODO Auto-generated method stub
		int updateCount;
		updateCount=this.getSqlMapClientTemplate().update("userTask.updateTask", userTask);
		return updateCount>0?true:false;
	}

	public List<UserTask> queryByStatus(int userId, int taskId, String updateTime,int status) {
		// TODO Auto-generated method stub
		Map <Object,Object> ids=new HashMap<Object, Object>();
		ids.put("userId", userId);
		ids.put("taskId",taskId);
		ids.put("updateTime", updateTime);
		ids.put("status", status);
		return this.getSqlMapClientTemplate().queryForList("userTask.findByStatus", ids);
	}

	public List<UserTask> queryByIdAndType(int userId, int taskId,
			String updateTime) {
		// TODO Auto-generated method stub
		Map <Object,Object> ids=new HashMap<Object, Object>();
		ids.put("userId", userId);
		ids.put("taskId",taskId);
		ids.put("updateTime", updateTime);
		return this.getSqlMapClientTemplate().queryForList("userTask.findByIdAndType", ids);
	}

	public List<UserTask> queryByIdAndNoTime(int userId, int taskId, int status) {
		// TODO Auto-generated method stub
		Map <Object,Object> ids=new HashMap<Object, Object>();
		ids.put("userId", userId);
		ids.put("taskId",taskId);
		ids.put("status", status);
		return this.getSqlMapClientTemplate().queryForList("userTask.findByIdAndTypeNoTime", ids);
	}

}
