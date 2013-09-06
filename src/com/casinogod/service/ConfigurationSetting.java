package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.Configuration;

public interface ConfigurationSetting {
	
	public List <Configuration> querAll();
	
	public void insertConfiguration(int winTimes,int rankSize,int bossRewardNum,int invitedReward,int invitedItem);
	
	public List <Configuration> queryById(int id);
	
	public boolean updateConfiguration(int winTimes,int rankSize,int id,int bossRewardNum,int invitedReward,int invitedItem);

}
