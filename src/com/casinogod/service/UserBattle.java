package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.UserBattleHistory;


public interface UserBattle {
	
	public List<UserBattleHistory> queryall();
	
	public boolean addUserBattle(long userId,String gameType, int winTimes,int lostTime,long winMoney,long lostMoney,int drawTimes);
	
	public boolean updateBattle(long userId,String gameType,int winTime, int lostTime,long winMoney,long lostMoney,int drawTimes);
	
	public List<UserBattleHistory> queryUser(long userId,String gameType);
	
	public List<UserBattleHistory> rankTimes(String gameType);
	
}
