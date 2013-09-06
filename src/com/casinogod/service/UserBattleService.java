package com.casinogod.service;

import java.util.List;

import com.casinogod.dao.UserBattleHistoryDAOImpl;
import com.casinogod.pojo.UserBattleHistory;

public class UserBattleService implements UserBattle {
	
	private UserBattleHistoryDAOImpl userBattleHistoryDAO;
	
	private UserBattleHistory userBattleHistory;
	
		
	public void setUserBattleHistory(UserBattleHistory userBattleHistory) {
		this.userBattleHistory = userBattleHistory;
	}

	public void setUserBattleHistoryDAO(
			UserBattleHistoryDAOImpl userBattleHistoryDAO) {
		this.userBattleHistoryDAO = userBattleHistoryDAO;
	}

	public List<UserBattleHistory> queryall() {
		// TODO Auto-generated method stub
		return userBattleHistoryDAO.querAll();
	}

	public boolean addUserBattle(long userId, String gameType, int winTimes,
			int lostTime, long winMoney, long lostMoney,int drawTimes) {
		// TODO Auto-generated method stub
		boolean flag=false;
		userBattleHistory.setUserId(userId);
		userBattleHistory.setGameType(gameType);
		userBattleHistory.setWinTimes(winTimes);
		userBattleHistory.setLostTime(lostTime);
		userBattleHistory.setWinMoney(winMoney);
		userBattleHistory.setLostMoney(lostMoney);
		userBattleHistory.setDrawTimes(drawTimes);
		userBattleHistoryDAO.insertBattleHistory(userBattleHistory);
		flag=true;
		return flag;
	}

	public boolean updateBattle(long userId, String gameType, int winTime,
			int lostTime, long winMoney, long lostMoney,int drawTimes) {
		// TODO Auto-generated method stub
		return userBattleHistoryDAO.updateUser(userId, gameType, winTime, lostTime, winMoney, lostMoney,drawTimes);
	}

	public List<UserBattleHistory> queryUser(long userId, String gameType) {
		// TODO Auto-generated method stub
		return userBattleHistoryDAO.quserUser(userId, gameType);
	}

	public List<UserBattleHistory> rankTimes(String gameType) {
		// TODO Auto-generated method stub
		return userBattleHistoryDAO.rankByTimes(gameType);
	}



}
