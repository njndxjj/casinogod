package com.casinogod.service;

import java.util.List;

import com.casinogod.pojo.BattleHistory;
import com.casinogod.pojo.UserBattleResult;


public interface UserResultProfile {
	
	public List<UserBattleResult> queryall();
	
	public boolean addUserResult(int userId,int battleType, int gameType,int totalResult,int winTotal,int loseTotal);

	
	public List <UserBattleResult> userBattleResult(int userId,int battleType);
	
	public List <UserBattleResult> userGameResult(int userId,int gameType);
	
	public List <UserBattleResult> userBattleGameResult(int userId,int battleType,int gameType);
	
	public boolean updateResult(int userId,int battleType, int gameType,int totalResult,int winTotal,int loseTotal);
	
	
	


}
