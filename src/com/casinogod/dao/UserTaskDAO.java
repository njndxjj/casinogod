package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.Task;
import com.casinogod.pojo.UserTask;

public interface UserTaskDAO {
   
	public List <UserTask> querAll();
	
	
	public void insertUserTask(UserTask userTask);

	
	public List <UserTask> queryById(int id);
	
	public boolean updateType(UserTask userTask);
	
	public List <UserTask> queryByStatus(int userId,int taskId,String updateTime,int status);
	
	public List <UserTask> queryByIdAndType(int userId,int taskId,String updateTime);
	
	public List <UserTask> queryByIdAndNoTime(int userId,int taskId,int status);
	
	
}
