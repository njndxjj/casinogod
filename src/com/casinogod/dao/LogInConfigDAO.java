package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.LogInRewardConfig;


public interface LogInConfigDAO {
   
	public List <LogInRewardConfig> querAll();
	
	public void insertItem(LogInRewardConfig loginConfig);
	
	public List <LogInRewardConfig> queryById(int id);
	
	public List <LogInRewardConfig> queryByDay(int day);
	
	public void deleteById(int id);
	
	public void updateById(LogInRewardConfig loginConfig);
	
}
