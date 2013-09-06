package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.EventConfig;
import com.casinogod.pojo.Task;


public interface TaskInterface {
	
	public List<Task> queryall();
	
	public List<Task> queryallStatus();
	
	public boolean addTask(int taskType,int taskRate, int itemId,int itemNum,int isDaily,int battleType,int gameType,String description);
	
	public List<Task> queryByType(int taskType);
	
	public List<Task> queryById(int taskId);
	
	public List<Task> queryByTypeAndRate(int taskType,int taskRate);
	
	public boolean  updateTask(Task task);
	
	public boolean deleteTask(int taskType,int taskRate);
	
}
