package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.Task;
import com.casinogod.pojo.UserTask;


public interface UserTaskInterface {
	
public List <UserTask> querAll();
	
	
	public void insertUserTask(int userId,int taskId,int status,String updateTime );

	
	public List <UserTask> queryById(int id);
	
	public boolean updateType(int userId,int taskId,int status,String updateTime);
	
	public List <UserTask> queryByStatus(int userId,int taskId,String updateTime,int status);
	
	public List <UserTask> queryByIdAndType(int userId,int taskId,String updateTime);
	
	public List <UserTask> queryByIdAndNoTime(int userId,int taskId,int status);
	
}
