package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.UserTaskDAOImpl;
import com.casinogod.pojo.Task;
import com.casinogod.pojo.UserTask;

public class UserTaskService implements UserTaskInterface {
	
	private UserTask userTask;
	private UserTaskDAOImpl userTaskDAO;
	


	public void setUserTask(UserTask userTask) {
		this.userTask = userTask;
	}

	public void setUserTaskDAO(UserTaskDAOImpl userTaskDAO) {
		this.userTaskDAO = userTaskDAO;
	}

	public List<UserTask> querAll() {
		// TODO Auto-generated method stub
		return userTaskDAO.querAll();
	}

	public void insertUserTask(int userId,int taskId,int status,String updateTime) {
		// TODO Auto-generated method stub
		userTask.setStatus(status);
		userTask.setTaskId(taskId);
		userTask.setUpdateTime(updateTime);
		userTask.setUserId(userId);
       userTaskDAO.insertUserTask(userTask);
	}

	public List<UserTask> queryById(int id) {
		// TODO Auto-generated method stub
		return userTaskDAO.queryById(id);
	}

	public boolean updateType(int userId,int taskId,int status,String updateTime) {
		// TODO Auto-generated method stub
		userTask.setUserId(userId);
		userTask.setStatus(status);
		userTask.setTaskId(taskId);
		userTask.setUpdateTime(updateTime);
		return userTaskDAO.updateType(userTask);
	}

	public List<UserTask> queryByStatus(int userId, int taskId, String updateTime,int status) {
		// TODO Auto-generated method stub
		return userTaskDAO.queryByStatus(userId, taskId, updateTime,status);
	}

	public List<UserTask> queryByIdAndType(int userId, int taskId,
			String updateTime) {
		// TODO Auto-generated method stub
		return userTaskDAO.queryByIdAndType(userId, taskId, updateTime);
	}

	public List<UserTask> queryByIdAndNoTime(int userId, int taskId, int status) {
		// TODO Auto-generated method stub
		return userTaskDAO.queryByIdAndNoTime(userId, taskId, status);
	}

}
