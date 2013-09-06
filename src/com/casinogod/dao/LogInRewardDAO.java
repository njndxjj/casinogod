package com.casinogod.dao;

import java.util.List;
import java.util.Map;

import com.casinogod.pojo.LogInReward;

public interface LogInRewardDAO {
   
	public List <LogInReward> querAll();
	
	public void insertLogIn(LogInReward logInReward);
	
	public List <LogInReward> queryById(long userId);
	
	public void updateById(LogInReward logInReward);
	
	public List <Long> queryByDate(Map <Object,Object> dates);
	
	public List <Long> queryUser();
	
}
