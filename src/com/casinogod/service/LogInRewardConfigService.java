package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.LogInConfigDAOImpl;
import com.casinogod.pojo.LogInRewardConfig;


public class LogInRewardConfigService implements RewardConfig {
	
private LogInConfigDAOImpl logInConfigDAO;
	
	private LogInRewardConfig logInRewardConfig;
	
	public void setLogInConfigDAO(LogInConfigDAOImpl logInConfigDAO) {
		this.logInConfigDAO = logInConfigDAO;
	}

	public void setLogInRewardConfig(LogInRewardConfig logInRewardConfig) {
		this.logInRewardConfig = logInRewardConfig;
	}

	public List<LogInRewardConfig> queryall() {
		// TODO Auto-generated method stub
		return logInConfigDAO.querAll();
	}

	public boolean add(int day, int reward) {
		// TODO Auto-generated method stub
		logInRewardConfig.setDay(day);
		logInRewardConfig.setReward(reward);
		logInConfigDAO.insertItem(logInRewardConfig);
		return true;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
		logInConfigDAO.deleteById(id);

	}

	public void update(int day, int reward,int id) {
		// TODO Auto-generated method stub
		logInRewardConfig.setId(id);
		logInRewardConfig.setDay(day);
		logInRewardConfig.setReward(reward);
		logInConfigDAO.updateById(logInRewardConfig);
	}

	public List<LogInRewardConfig> queryByDay(int day) {
		// TODO Auto-generated method stub
		return logInConfigDAO.queryByDay(day);
	}
	
	

}
