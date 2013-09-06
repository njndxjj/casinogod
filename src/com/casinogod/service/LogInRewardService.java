package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casinogod.dao.LogInRewardDAOImpl;
import com.casinogod.pojo.LogInReward;



public class LogInRewardService implements LogInRewardPro {
	
	private LogInRewardDAOImpl logInRewardDAO;
	
	private LogInReward logInReward;
	
	public void setLogInRewardDAO(LogInRewardDAOImpl logInRewardDAO) {
		this.logInRewardDAO = logInRewardDAO;
	}

	public void setLogInReward(LogInReward logInReward) {
		this.logInReward = logInReward;
	}

	public List<LogInReward> queryall() {
		// TODO Auto-generated method stub
		return logInRewardDAO.querAll();
	}

	public boolean add(long userId, String logInTime, int lastTimes) {
		// TODO Auto-generated method stub
		logInReward.setUserId(userId);
		logInReward.setLogInTime(logInTime);
		logInReward.setLastTimes(lastTimes);
		logInRewardDAO.insertLogIn(logInReward);
		return true;
	}

	public void update(long userId, String logInTime, int lastTimes) {
		// TODO Auto-generated method stub
		logInReward.setUserId(userId);
		logInReward.setLogInTime(logInTime);
		logInReward.setLastTimes(lastTimes);
		logInRewardDAO.updateById(logInReward);
	}

	public List<LogInReward> queryById(long userId) {
		// TODO Auto-generated method stub
		return logInRewardDAO.queryById(userId);
	}

	public List<Long> queryDate(String startTime, String endTime) {
		// TODO Auto-generated method stub
		
		Map <Object,Object> dates=new HashMap<Object, Object>();
		dates.put("endTime", endTime);
		dates.put("createTime", startTime);
		return logInRewardDAO.queryByDate(dates);
	}

	public List<Long> queryUser() {
		// TODO Auto-generated method stub
		return logInRewardDAO.queryUser();
	}
	
	

}
