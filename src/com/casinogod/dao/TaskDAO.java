package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.Item;
import com.casinogod.pojo.Task;

public interface TaskDAO {
   
	public List <Task> querAll();
	
	public List <Task> querAllStatus();
	
	public void insertTask(Task task);
	
	public List <Task> queryByType(int taskType);
	
	public List <Task> queryById(int taskId);
	
	public boolean updateType(Task task);
	
	public List <Task> queryByTypeAndRate(int taskType,int taskRate);
	
	public boolean deleteType(int taskType,int taskRate);
	
}
