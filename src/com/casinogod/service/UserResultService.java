package com.casinogod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.casinogod.dao.UserBattleResultDAOImpl;
import com.casinogod.pojo.UserBattleResult;

public class UserResultService implements UserResultProfile {
	
	private UserBattleResult userBattleResult;
	
	private UserBattleResultDAOImpl userBattleResultDAO;
	

	public void setUserBattleResult(UserBattleResult userBattleResult) {
		this.userBattleResult = userBattleResult;
	}

	public void setUserBattleResultDAO(UserBattleResultDAOImpl userBattleResultDAO) {
		this.userBattleResultDAO = userBattleResultDAO;
	}

	public List<UserBattleResult> queryall() {
		// TODO Auto-generated method stub
		return userBattleResultDAO.querAll();
	}

	public boolean addUserResult(int userId, int battleType, int gameType,
			int totalResult, int winTotal, int loseTotal) {
		// TODO Auto-generated method stub
		userBattleResult.setBattleType(battleType);
		userBattleResult.setGameType(gameType);
		userBattleResult.setLoseTotal(loseTotal);
		userBattleResult.setTotalResult(totalResult);
		userBattleResult.setUserId(userId);
		userBattleResult.setWinTotal(winTotal);
		userBattleResultDAO.insertBattleResult(userBattleResult);
		return true;
	}

	public List<UserBattleResult> userBattleResult(int userId, int battleType) {
		// TODO Auto-generated method stub
		
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("userId", userId);
		map.put("battleType", battleType);
		return userBattleResultDAO.queryUserAndBattle(map);
	}

	public List<UserBattleResult> userGameResult(int userId, int gameType) {
		// TODO Auto-generated method stub
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("userId", userId);
		map.put("gameType", gameType);
		return userBattleResultDAO.queryUserAndGame(map);
	}

	public List<UserBattleResult> userBattleGameResult(int userId,
			int battleType, int gameType) {
		// TODO Auto-generated method stub
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("userId", userId);
		map.put("battleType", battleType);
		map.put("gameType", gameType);
		return userBattleResultDAO.queryUserAndGame(map);
	}

	public boolean updateResult(int userId, int battleType, int gameType,
			int totalResult, int winTotal, int loseTotal) {
		// TODO Auto-generated method stub
		userBattleResult.setBattleType(battleType);
		userBattleResult.setGameType(gameType);
		userBattleResult.setLoseTotal(loseTotal);
		userBattleResult.setTotalResult(totalResult);
		userBattleResult.setUserId(userId);
		userBattleResult.setWinTotal(winTotal);
		return userBattleResultDAO.updateBattleResult(userBattleResult);
	}

}
