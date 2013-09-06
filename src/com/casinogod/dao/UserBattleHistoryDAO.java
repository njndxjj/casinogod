package com.casinogod.dao;

import java.util.List;

import com.casinogod.pojo.UserBattleHistory;

public interface UserBattleHistoryDAO {
   	
	public List <UserBattleHistory> quserUser(long userId,String gameType);
	
	public List <UserBattleHistory> querAll();
	
	public List <UserBattleHistory> rankByTimes(String gameType);
	
	public boolean updateUser(long userId,String gameType,int winTimes,int lostTime,long winMoney,long lostMoney,int drawTimes);
	
	public void insertBattleHistory(UserBattleHistory userBattle);
	
}
