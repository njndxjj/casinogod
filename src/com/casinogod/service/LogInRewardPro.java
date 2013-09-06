package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.LogInReward;



public interface LogInRewardPro {
	
	public List<LogInReward> queryall();
	
	public boolean add(long userId,String logInTime,int lastTimes);
		
	public void update(long userId,String logInTime,int lastTimes);
	
	public List <LogInReward> queryById(long userId);
	
	public List <Long> queryDate(String startTime,String endTime);
	
	public List <Long> queryUser();
}
