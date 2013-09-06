package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.LogInRewardConfig;

public interface RewardConfig {
	
	public List<LogInRewardConfig> queryall();
	
	public List<LogInRewardConfig> queryByDay(int day);
	
	public boolean add(int day,int reward);
	
	public void delete (int id);
	
	public void update(int day,int reward,int id);
	
	
}
