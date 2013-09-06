package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.TaskDAOImpl;
import com.casinogod.pojo.Task;

public class TaskService implements TaskInterface {
	
	private Task task;
	private TaskDAOImpl taskDAO;
	

	public void setTask(Task task) {
		this.task = task;
	}

	public void setTaskDAO(TaskDAOImpl taskDAO) {
		this.taskDAO = taskDAO;
	}

	public List<Task> queryall() {
		// TODO Auto-generated method stub
		return taskDAO.querAll();
	}

	public boolean addTask(int taskType, int taskRate, int itemId, int itemNum,int isDaily,int battleType,int gameType,String description) {
		// TODO Auto-generated method stub
		task.setItemId(itemId);
		task.setItemNum(itemNum);
		task.setTaskRate(taskRate);
		task.setTaskType(taskType);
		task.setStatus(0);
		task.setIsDaily(isDaily);
		task.setBattleType(battleType);
		task.setGameType(gameType);
		task.setDescription(description);
		taskDAO.insertTask(task);
		return true;
	}

	public List<Task> queryByType(int taskType) {
		// TODO Auto-generated method stub
		return taskDAO.queryByType(taskType);
	}

	public boolean updateTask(Task task) {
		// TODO Auto-generated method stub
		return taskDAO.updateType(task);
	}

	public boolean deleteTask(int taskType, int taskRate) {
		// TODO Auto-generated method stub
		return taskDAO.deleteType(taskType, taskRate);
	}

	public List<Task> queryByTypeAndRate(int taskType, int taskRate) {
		// TODO Auto-generated method stub
		return taskDAO.queryByTypeAndRate(taskType, taskRate);
	}

	public List<Task> queryallStatus() {
		// TODO Auto-generated method stub
		return taskDAO.querAllStatus();
	}

	public List<Task> queryById(int taskId) {
		// TODO Auto-generated method stub
		return taskDAO.queryById(taskId);
	}

}
